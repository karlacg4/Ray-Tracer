/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer;

import edu.up.isgc.raytracer.objects.Object3D;

/**
 *
 * @author Jafet Rodríguez
 */

/**
 * class to crate intersections that can be used i any abject to detect coalitions
 */
public class Intersection {

    private double distance;
    private Vector3D normal;
    private Vector3D position;
    private Object3D object;

    public Intersection(Vector3D position, double distance, Vector3D normal, Object3D object) {
        setPosition(position);
        setDistance(distance);
        setNormal(normal);
        setObject(object);
    }
    /**
     * Set and get methods to access class attributes
     */

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vector3D getNormal() {
        return normal;
    }

    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Object3D getObject() {
        return object;
    }

    public void setObject(Object3D object) {
        this.object = object;
    }
}
