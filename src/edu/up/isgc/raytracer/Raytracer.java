/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer;

import edu.up.isgc.raytracer.lights.DirectionalLight;
import edu.up.isgc.raytracer.lights.Light;
import edu.up.isgc.raytracer.lights.PointLight;
import edu.up.isgc.raytracer.objects.*;
import edu.up.isgc.raytracer.tools.OBJReader;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Jafet Rodríguez
 * @author Karla Carpinteyro
 */

/**
 * Main Runner Class
 */
public class Raytracer {

    public static void main(String[] args) {
        System.out.println(new Date());
        //Scene setup
        Scene scene01 = new Scene();
        Material reflective = new Material(80, 0.035, true, 0.2, 0);
        Material notreflective = new Material(80, 0.035, false, 0, 0);
        Material refractive = new Material(80, 0.034, false, 0, 1.5);
        scene01.setCamera(new Camera(new Vector3D(0, 0, -8), 160, 160, 1600, 1600, 0f, 50f));
        scene01.addLight(new DirectionalLight(Vector3D.ZERO(), new Vector3D(0.0, 0.0, 6.5), Color.WHITE, 1));

        //SCENE
        scene01.addLight(new PointLight(new Vector3D(0f, 2f, 0f), new Color(196, 95, 18), 5));

        scene01.addObject(OBJReader.GetPolygon("farmG.obj", new Vector3D(0.2f, -1f, 2f), new Color(187, 171, 47), notreflective));
        scene01.addObject(OBJReader.GetPolygon("windowFarm.obj", new Vector3D(0.2f, -1f, 2f), Color.WHITE, reflective));
        scene01.addObject(OBJReader.GetPolygon("redFarm.obj", new Vector3D(0.2f, -1.2f, 2f), new Color(187, 23, 17), notreflective));
        scene01.addObject(OBJReader.GetPolygon("cow.obj", new Vector3D(2f, -1f, 1f), Color.WHITE, notreflective));
        scene01.addObject(new Sphere(new Vector3D(0f, 0.5f, -2f), 0.5f, Color.BLACK, refractive));
//


//        scene01.addObject(new Sphere(new Vector3D(0f, 0.5f, 4f), 0.5f, Color.BLACK, refractive));
//        scene01.addObject(OBJReader.GetPolygon("firstArch.obj", new Vector3D(0f, -1f, 5f), new Color(255, 215, 113), material2));
//        scene01.addObject(OBJReader.GetPolygon("secondArch.obj", new Vector3D(0f, -1f, 5f), new Color(56, 124, 144), refractive));
//        scene01.addObject(OBJReader.GetPolygon("Arch.obj", new Vector3D(0f, -1f, 5f), new Color(128, 17, 17), reflective));
//        scene01.addObject(OBJReader.GetPolygon("skinB.obj", new Vector3D(0f, -1f, 3.5f), new Color(255, 162, 93), notreflective));
//        scene01.addObject(OBJReader.GetPolygon("coatB.obj", new Vector3D(0f, -1f, 4f), new Color(239, 210, 34), reflective));
//        scene01.addObject(OBJReader.GetPolygon("hairB.obj", new Vector3D(0f, -1f, 3.5f), new Color(248, 66, 72), notreflective));
//        scene01.addObject(OBJReader.GetPolygon("pinkB.obj", new Vector3D(0f, -1f, 4f), new Color(196, 14, 49), notreflective));
//        scene01.addObject(OBJReader.GetPolygon("eyesB.obj", new Vector3D(0f, -1f, 3.5f), Color.WHITE, reflective));
//
        //SCENE2
//        scene01.addObject(OBJReader.GetPolygon("botella.obj", new Vector3D(0f, -1f, -2f), new Color(4, 69, 10), reflective));
//        scene01.addObject(OBJReader.GetPolygon("cup.obj", new Vector3D(0.5f, -1f, -2f), Color.BLACK, refractive));
//        scene01.addObject(OBJReader.GetPolygon("jarr.obj", new Vector3D(0.5f, -1f, -1f), new Color(142, 90, 169), reflective));
//        scene01.addObject(OBJReader.GetPolygon("jarr2.obj", new Vector3D(0f, -1f, -2f), new Color(60, 129, 107), metal));
//        scene01.addObject(OBJReader.GetPolygon("vela.obj", new Vector3D(-0.2f, -1f, -1f), new Color(201, 208, 167), notreflective));
//        scene01.addObject(OBJReader.GetPolygon("baseVela.obj", new Vector3D(-0.2f, -1f, -1f), new Color(169, 137, 21), reflective));
//        scene01.addObject(OBJReader.GetPolygon("tea.obj", new Vector3D(0.1f, -1f, -2f), Color.MAGENTA, reflective));
//        scene01.addObject(OBJReader.GetPolygon("taza1.obj", new Vector3D(0.1f, -1f, -2f), Color.PINK, refractive));

        scene01.addObject(new Polygon(new Vector3D(0, -1, 0), new Triangle[]{new Triangle(new Vector3D(-8, 0, -12), new Vector3D(8, 0, -12), new Vector3D(8, 0, 12)),
                new Triangle(new Vector3D(8, 0, 12), new Vector3D(-8, 0, 12), new Vector3D(-8, 0, -12))}, Color.GREEN, reflective));

        //Calling method and generating the image
        BufferedImage image = raytrace(scene01, Color.BLACK);
        File outputImage = new File("image.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (IOException ioe) {
            System.out.println("Something failed");
        }
        System.out.println(new Date());
    }

    /**
     * Method that calculates specular value by calculating a half vector and applying formulas
     * @param viewer
     * @param light
     * @param normal
     * @param shininess
     * @return
     */
    public static double specular(Vector3D viewer, Vector3D light, Vector3D normal, double shininess) {
        Vector3D halfVector = Vector3D.scalarMultiplication(Vector3D.add(light, viewer), 1 / Vector3D.magnitude(Vector3D.add(light, viewer)));
        return Math.pow(Vector3D.dotProduct(normal, halfVector), shininess);
    }
    /**
     * Method that creates a buffered image of the scene, calculating lights, shadow, blinn-phong, reflection and refraction
     * @param scene
     * @param bgColor
     * @return
     */
    public static BufferedImage raytrace(Scene scene, Color bgColor) {
        Camera mainCamera = scene.getCamera();
        ArrayList<Light> lights = scene.getLights();
        float[] nearFarPlanes = mainCamera.getNearFarPlanes();
        BufferedImage image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        ArrayList<Object3D> objects = scene.getObjects();
        Vector3D[][] positionsToRaytrace = mainCamera.calculatePositionsToRay();
        //For loop to check each ray
        for (int i = 0; i < positionsToRaytrace.length; i++) {
            System.out.println(i + "/" + positionsToRaytrace.length);
            for (int j = 0; j < positionsToRaytrace[i].length; j++) {
                double x = positionsToRaytrace[i][j].getX() + mainCamera.getPosition().getX();
                double y = positionsToRaytrace[i][j].getY() + mainCamera.getPosition().getY();
                double z = positionsToRaytrace[i][j].getZ() + mainCamera.getPosition().getZ();
                Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
                float cameraZ = (float) mainCamera.getPosition().getZ();
                Intersection closestIntersection = raycast(ray, objects, null, new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
                //Background color
                Color pixelColor = bgColor;
                if (closestIntersection != null) {
                    pixelColor = Color.BLACK;
                    //Calculate Blinn phong
                    for (Light light : lights) {
                        float nDotL = light.getNDotL(closestIntersection);
                        float intensity = (float) light.getIntensity() * nDotL;
                        float lightFallout = (float) (intensity / Math.pow(Vector3D.magnitude(Vector3D.substract(closestIntersection.getPosition(), light.getPosition())), 1));
                        Color lightColor = light.getColor();
                        Color objColor = closestIntersection.getObject().getColor();
                        float[] lightColors;
                        float[] objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                        //Shadows
                        Ray shadow = new Ray(closestIntersection.getPosition(), Vector3D.substract(light.getPosition(), closestIntersection.getPosition()));
                        //Calculate light if is Directional light
                        if (light instanceof DirectionalLight) {
                            shadow = new Ray(closestIntersection.getPosition(), Vector3D.scalarMultiplication(((DirectionalLight) light).getDirection(), -1));
                        }
                        Intersection shadowIntersection = raycast(shadow, objects, closestIntersection.getObject(), new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
                        //Ambient
                        for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                            objColors[colorIndex] *= closestIntersection.getObject().getMaterial().getAmbient();
                        }
                        Color ambient = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
                        lightColors = new float[]{lightColor.getRed() / 255.0f, lightColor.getGreen() / 255.0f, lightColor.getBlue() / 255.0f};
                        objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                        //Specular
                        double specular = specular(mainCamera.getPosition(), light.getPosition(), closestIntersection.getNormal(), closestIntersection.getObject().getMaterial().getShininess());
                        for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                            objColors[colorIndex] *= (lightFallout + specular) * lightColors[colorIndex];
                        }
                        Color diffuse = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
                        //Add elements of blinn phong
                        pixelColor = addColor(pixelColor, diffuse);
                        pixelColor = addColor(pixelColor, ambient);
                        //Shadows
                        if (shadowIntersection != null) {
                            pixelColor = Color.BLACK;
                        }
                        //Reflective recursive
                        if (closestIntersection.getObject().getMaterial().isReflective()) {
                            pixelColor = addColor(pixelColor, reflection(scene, new Ray(mainCamera.getPosition(), Vector3D.substract(closestIntersection.getPosition(), mainCamera.getPosition())), closestIntersection, 5));
                        }
                        //Refraction recursive
                        if (closestIntersection.getObject().getMaterial().getRefractiveIndex() > 0) {
                            pixelColor = addColor(pixelColor, refraction(scene, new Ray(mainCamera.getPosition(), Vector3D.substract(closestIntersection.getPosition(), mainCamera.getPosition())), closestIntersection));
                        }
                    }
                }
                image.setRGB(i, j, pixelColor.getRGB());
            }
        }

        return image;
    }

    /**
     * Method that calculates the color of the reflection of an object
     * @param scene
     * @param start
     * @param closestIntersection
     * @param times
     * @return
     */
    public static Color reflection(Scene scene, Ray start, Intersection closestIntersection, int times) {
        //Reflction formulas
        Vector3D incident = Vector3D.normalize(start.getDirection());
        Vector3D reflectionV = Vector3D.normalize(Vector3D.substract(incident, Vector3D.scalarMultiplication(closestIntersection.getNormal(), Vector3D.dotProduct(closestIntersection.getNormal(), incident) * 2)));
        Ray reflectionRay = new Ray(closestIntersection.getPosition(), reflectionV);
        Intersection reflection = raycast(reflectionRay, scene.getObjects(), closestIntersection.getObject(), new float[]{(float) scene.getCamera().getPosition().getZ() + scene.getCamera().getNearFarPlanes()[0], (float) scene.getCamera().getPosition().getZ() + scene.getCamera().getNearFarPlanes()[1]});
        Color pixelColor = Color.BLACK;
        //Calculate with the new vector reflection
        if (reflection != null) {
            pixelColor = Color.BLACK;
            for (Light light : scene.getLights()) {
                float nDotL = light.getNDotL(reflection);
                float intensity = (float) light.getIntensity() * nDotL;
                float lightFallout = (float) (intensity / Math.pow(Vector3D.magnitude(Vector3D.substract(reflection.getPosition(), light.getPosition())), 1));
                Color lightColor = light.getColor();
                Color objColor = reflection.getObject().getColor();
                float[] lightColors;
                float[] objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                //Shadows
                Ray shadow = new Ray(reflection.getPosition(), Vector3D.substract(light.getPosition(), reflection.getPosition()));
                if (light instanceof DirectionalLight) {
                    shadow = new Ray(reflection.getPosition(), Vector3D.scalarMultiplication(((DirectionalLight) light).getDirection(), -1));
                }
                Intersection shadowIntersection = raycast(shadow, scene.getObjects(), reflection.getObject(), new float[]{(float) scene.getCamera().getPosition().getZ() + scene.getCamera().getNearFarPlanes()[0], (float) scene.getCamera().getPosition().getZ() + scene.getCamera().getNearFarPlanes()[1]});
                //Ambient
                for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                    objColors[colorIndex] *= reflection.getObject().getMaterial().getAmbient();
                }
                Color ambient = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
                lightColors = new float[]{lightColor.getRed() / 255.0f, lightColor.getGreen() / 255.0f, lightColor.getBlue() / 255.0f};
                objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                //Specular
                double specular = specular(scene.getCamera().getPosition(), light.getPosition(), reflection.getNormal(), reflection.getObject().getMaterial().getShininess());
                for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                    objColors[colorIndex] *= (lightFallout + specular) * lightColors[colorIndex];
                }
                Color diffuse = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
                pixelColor = addColor(pixelColor, diffuse);
                pixelColor = addColor(pixelColor, ambient);
                pixelColor = multiplyColor(pixelColor, 1 - closestIntersection.getObject().getMaterial().getReflectiveness());
                //Shadows
                if (shadowIntersection != null) {
                    pixelColor = Color.BLACK;
                }
            }
            //recursive if the object is reflective
            if (reflection.getObject().getMaterial().isReflective() && times > 0) {
                times--;
                pixelColor = addColor(pixelColor, reflection(scene, reflectionRay, reflection, times));
            } else {
                //Calls method refraction if the object is refractive
                if (reflection.getObject().getMaterial().getRefractiveIndex() > 0) {
                    pixelColor = addColor(pixelColor, refraction(scene, reflectionRay, reflection));
                }
                return pixelColor;
            }

        }
        return pixelColor;
    }

    /**
     * Method that calculates the color of the refraction of an object
     * @param scene
     * @param start
     * @param closestIntersection
     * @return
     */
    public static Color refraction(Scene scene, Ray start, Intersection closestIntersection) {
        //Refraction formulas
        Vector3D incident = Vector3D.normalize(start.getDirection());
        double n = 1 / closestIntersection.getObject().getMaterial().getRefractiveIndex();
        double c1 = Vector3D.dotProduct(closestIntersection.getNormal(), incident);
        double c2 = Math.sqrt(1 - Math.pow(n, 2) * (1 - Math.pow(c1, 2)));
        Vector3D refractionV = Vector3D.add(Vector3D.scalarMultiplication(incident, n), Vector3D.scalarMultiplication(closestIntersection.getNormal(), (n * c1) - c2));
        Ray refractionRay = new Ray(closestIntersection.getPosition(), refractionV);
        Intersection refraction = raycast(refractionRay, scene.getObjects(), closestIntersection.getObject(), new float[]{(float) scene.getCamera().getPosition().getZ() + scene.getCamera().getNearFarPlanes()[0], (float) scene.getCamera().getPosition().getZ() + scene.getCamera().getNearFarPlanes()[1]});
        Color pixelColor = Color.BLACK;
        //Calculate with the new vector refraction
        if (refraction != null) {
            pixelColor = Color.BLACK;
            for (Light light : scene.getLights()) {
                float nDotL = light.getNDotL(refraction);
                float intensity = (float) light.getIntensity() * nDotL;
                float lightFallout = (float) (intensity / Math.pow(Vector3D.magnitude(Vector3D.substract(refraction.getPosition(), light.getPosition())), 1));
                Color lightColor = light.getColor();
                Color objColor = refraction.getObject().getColor();
                float[] lightColors;
                float[] objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                //Shadows
                Ray shadow = new Ray(refraction.getPosition(), Vector3D.substract(light.getPosition(), refraction.getPosition()));
                if (light instanceof DirectionalLight) {
                    shadow = new Ray(refraction.getPosition(), Vector3D.scalarMultiplication(((DirectionalLight) light).getDirection(), -1));
                }
                Intersection shadowIntersection = raycast(shadow, scene.getObjects(), refraction.getObject(), new float[]{(float) scene.getCamera().getPosition().getZ() + scene.getCamera().getNearFarPlanes()[0], (float) scene.getCamera().getPosition().getZ() + scene.getCamera().getNearFarPlanes()[1]});
                //Ambient
                for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                    objColors[colorIndex] *= refraction.getObject().getMaterial().getAmbient();
                }
                Color ambient = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
                lightColors = new float[]{lightColor.getRed() / 255.0f, lightColor.getGreen() / 255.0f, lightColor.getBlue() / 255.0f};
                objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                //Specular
                double specular = specular(scene.getCamera().getPosition(), light.getPosition(), refraction.getNormal(), refraction.getObject().getMaterial().getShininess());
                for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                    objColors[colorIndex] *= (lightFallout + specular) * lightColors[colorIndex];
                }
                Color diffuse = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
                pixelColor = addColor(pixelColor, diffuse);
                pixelColor = addColor(pixelColor, ambient);
                pixelColor = multiplyColor(pixelColor, 1 - closestIntersection.getObject().getMaterial().getReflectiveness());
                //Shadows
                if (shadowIntersection != null) {
                    pixelColor = Color.BLACK;
                }
            }
            //Call method reflection if the object is reflective
            if (refraction.getObject().getMaterial().isReflective()) {
                pixelColor = addColor(pixelColor, reflection(scene, refractionRay, refraction, 5));
            }
            //Recursive if the object is refractive
            if (refraction.getObject().getMaterial().getRefractiveIndex() > 0) {
                pixelColor = addColor(pixelColor, refraction(scene, refractionRay, refraction));
            } else {
                return pixelColor;
            }

        }
        return pixelColor;
    }

    /**
     * Method to restrict value
     * @param value
     * @param min
     * @param max
     * @return
     */
    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /**
     * Method that multiplies a Color times a scalar
     * @param original
     * @param scalar
     * @return
     */
    public static Color multiplyColor(Color original, double scalar) {
        float red = clamp((float) ((original.getRed() / 255.0f) * scalar), 0, 1);
        float green = clamp((float) ((original.getGreen() / 255.0f) * scalar), 0, 1);
        float blue = clamp((float) ((original.getBlue() / 255.0f) * scalar), 0, 1);
        return new Color(red, green, blue);
    }

    /**
     * Method that adds two colors
     * @param original
     * @param otherColor
     * @return
     */
    public static Color addColor(Color original, Color otherColor) {
        float red = clamp((original.getRed() / 255.0f) + (otherColor.getRed() / 255.0f), 0, 1);
        float green = clamp((original.getGreen() / 255.0f) + (otherColor.getGreen() / 255.0f), 0, 1);
        float blue = clamp((original.getBlue() / 255.0f) + (otherColor.getBlue() / 255.0f), 0, 1);
        return new Color(red, green, blue);
    }

    /**
     * Method that casts a ray and checks if it collided with anything within the clipping planes range
     * @param ray
     * @param objects
     * @param caster
     * @param clippingPlanes
     * @return
     */
    public static Intersection raycast(Ray ray, ArrayList<Object3D> objects, Object3D caster, float[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int k = 0; k < objects.size(); k++) {
            Object3D currentObj = objects.get(k);
            if (caster == null || !currentObj.equals(caster)) {
                Intersection intersection = currentObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersection.getPosition().getZ() >= clippingPlanes[0] &&
                                    intersection.getPosition().getZ() <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }
            }
        }

        return closestIntersection;
    }
}
