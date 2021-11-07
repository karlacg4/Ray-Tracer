/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer.objects;

import edu.up.isgc.raytracer.Intersection;
import edu.up.isgc.raytracer.Ray;
import edu.up.isgc.raytracer.Vector3D;
import edu.up.isgc.raytracer.objects.Object3D;

import java.awt.*;

/**
 * @author Jafet Rodríguez
 */

/**
 * public class Camera extends Object3D
 * implement this class to create a camera and set the field of view, clipping planes  and resolution of the render
 */
public class Camera extends Object3D {
    // 0 is fovh
    // 1 is fovv
    private float[] fieldOfView = new float[2];
    private float defaultZ = 15f;
    // 0 is width
    // 1 is height
    private int[] resolution;
    private float[] nearFarPlanes = new float[2];

    /**
     * Class constructor
     * @param position Vector3D
     * @param fieldOfViewHorizontal float
     * @param fieldOfViewVertical float
     * @param widthResolution int
     * @param heightResolution int
     * @param nearPlane float
     * @param farPlane float
     */
    public Camera(Vector3D position, float fieldOfViewHorizontal, float fieldOfViewVertical, int widthResolution, int heightResolution, float nearPlane, float farPlane) {
        super(position, Color.black);
        setFieldOfViewHorizontal(fieldOfViewHorizontal);
        setFieldOfViewVertical(fieldOfViewVertical);
        setResolution(new int[]{widthResolution, heightResolution});
        setNearFarPlanes(new float[]{nearPlane, farPlane});
    }

    /**
     * Set and get methods to access class attributes
     */

    public float getFieldOfViewHorizontal() {
        return fieldOfView[0];
    }

    public void setFieldOfViewHorizontal(float fov) {
        fieldOfView[0] = fov;
    }

    public float getFieldOfViewVertical() {
        return fieldOfView[1];
    }

    public void setFieldOfViewVertical(float fov) {
        fieldOfView[1] = fov;
    }

    public float getDefaultZ() {
        return defaultZ;
    }

    public void setDefaultZ(float defaultZ) {
        this.defaultZ = defaultZ;
    }

    public int[] getResolution() {
        return resolution;
    }

    public void setResolution(int[] resolution) {
        this.resolution = resolution;
    }

    public int getResolutionWidth() {
        return getResolution()[0];
    }

    public int getResolutionHeight() {
        return getResolution()[1];
    }

    public float[] getNearFarPlanes() {
        return nearFarPlanes;
    }

    public void setNearFarPlanes(float[] nearFarPlanes) {
        this.nearFarPlanes = nearFarPlanes;
    }

    /**
     * Method that optimizes rendering by calculating the position of the rays inside of the range tah is indicated by the camera attributes
     * @return
     */
    public Vector3D[][] calculatePositionsToRay() {
        float angleMaxX = 90 - (getFieldOfViewHorizontal() / 2f);
        float radiusMaxX = getDefaultZ() / (float) Math.cos(Math.toRadians(angleMaxX));

        float maxX = (float) Math.sin(Math.toRadians(angleMaxX)) * radiusMaxX;
        float minX = -maxX;

        float angleMaxY = 90 - (getFieldOfViewVertical() / 2f);
        float radiusMaxY = getDefaultZ() / (float) Math.cos(Math.toRadians(angleMaxY));

        float maxY = (float) Math.sin(Math.toRadians(angleMaxY)) * radiusMaxY;
        float minY = -maxY;

        Vector3D[][] positions = new Vector3D[getResolutionWidth()][getResolutionHeight()];
        float posZ = getDefaultZ();
        for (int x = 0; x < positions.length; x++) {
            for (int y = 0; y < positions[x].length; y++) {
                float posX = minX + (((maxX - minX) / (float) getResolutionWidth()) * x);
                float posY = maxY - (((maxY - minY) / (float) getResolutionHeight()) * y);
                positions[x][y] = new Vector3D(posX, posY, posZ);
            }
        }

        return positions;
    }


    @Override
    public Intersection getIntersection(Ray ray) {
        return new Intersection(Vector3D.ZERO(), -1, Vector3D.ZERO(), null);
    }
}
