/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer.lights;

import edu.up.isgc.raytracer.Intersection;
import edu.up.isgc.raytracer.Ray;
import edu.up.isgc.raytracer.Vector3D;
import edu.up.isgc.raytracer.objects.Object3D;

import java.awt.*;

/**
 *
 * @author Jafet Rodríguez
 */

/**
 * public abstract class Light extends Object3D
 * Abstract class with general attributes of light to be able to calculate specific lights
 */
public abstract class Light extends Object3D {
    private double intensity;

    /**
     * Light constructor
     * @param position Vector3D
     * @param color Color
     * @param intensity Double
     */

    public Light(Vector3D position, Color color, double intensity){
        super(position, color);
        setIntensity(intensity);
    }

    /**
     * Set and get methods to access class attributes
     */
    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public Intersection getIntersection(Ray ray){
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }

    public abstract float getNDotL(Intersection intersection);


}
