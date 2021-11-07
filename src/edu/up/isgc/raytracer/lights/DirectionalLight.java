/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer.lights;

import edu.up.isgc.raytracer.Intersection;
import edu.up.isgc.raytracer.Vector3D;

import java.awt.*;

/**
 *
 * @author Jafet Rodríguez
 */

/**
 *public class DirectionalLight extends Light
 * creates a Light with an specific direction
 */
public class DirectionalLight extends Light {
    private Vector3D direction;


    /**
     * Set and get methods to access class attributes
     */
    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }

    /**
     * Class constructor, it receives the following to set the atributes
     * @param position Vector3D
     * @param direction Vector 3D
     * @param color Color
     * @param intensity double
     */
    public DirectionalLight(Vector3D position, Vector3D direction, Color color, double intensity){
        super(position, color, intensity);
        setDirection(Vector3D.normalize(direction));
    }

    /**
     * Override of the function getNDotL from class Light to return the correct light implementing formulas for directional Light
     * @param intersection Intersection
     * @return float
     */
    @Override
    public float getNDotL(Intersection intersection) {
        return (float)Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(getDirection(), -1.0)), 0.0);
    }
}
