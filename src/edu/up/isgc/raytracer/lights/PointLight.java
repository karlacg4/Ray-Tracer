package edu.up.isgc.raytracer.lights;
import edu.up.isgc.raytracer.Intersection;
import edu.up.isgc.raytracer.Vector3D;

import java.awt.*;

/**
 * @Author Karla Carpinteyro
 *
 */



public class PointLight extends Light {

    /**
     * Class constructor
     * @param position Vector3D
     * @param color Color
     * @param intensity double
     */
    public PointLight(Vector3D position,  Color color, double intensity){
        super(position, color, intensity);

    }

    /**
     * Override of method getNDotL implementing formulas for point light
     * @param intersection Intersection
     * @return double
     */
    @Override
    public float getNDotL(Intersection intersection) {
        Vector3D IsubstractL = Vector3D.normalize(Vector3D.substract(intersection.getPosition(), this.getPosition()));
        return (float)Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(IsubstractL, -1.0)), 0.0);
    }
}
