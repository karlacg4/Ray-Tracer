/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer.objects;

import edu.up.isgc.raytracer.Intersection;
import edu.up.isgc.raytracer.Ray;
import edu.up.isgc.raytracer.Vector3D;

import java.awt.*;

/**
 *
 * @author Jafet Rodríguez
 */
public class Sphere extends Object3D {

    private float radius;

    /**
     * Set and get methods to access class attributes
     */

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Class constructor
     * @param position Vector3D
     * @param radius float
     * @param color Color
     * @param material Material
     */
    public Sphere(Vector3D position, float radius, Color color, Material material) {
        super(position, color, material);
        setRadius(radius);
    }

    /**
     * Override methdo to get the intersection of a sphere using math formulas
     * @param ray Ray
     * @return
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        double distance = -1;
        Vector3D normal = Vector3D.ZERO();
        Vector3D position = Vector3D.ZERO();

        Vector3D directionSphereRay = Vector3D.substract(ray.getOrigin(), getPosition());
        double firstP = Vector3D.dotProduct(ray.getDirection(), directionSphereRay);
        double secondP = Math.pow(Vector3D.magnitude(directionSphereRay), 2);
        double intersection = Math.pow(firstP, 2) - secondP + Math.pow(getRadius(), 2);

        if(intersection >= 0){
            double sqrtIntersection = Math.sqrt(intersection);
            double part1 = -firstP + sqrtIntersection;
            double part2 = -firstP - sqrtIntersection;

            distance = Math.min(part1, part2);
            position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
            normal = Vector3D.normalize(Vector3D.substract(position, getPosition()));
        } else {
            return null;
        }

        return new Intersection(position, distance, normal, this);
    }
}
