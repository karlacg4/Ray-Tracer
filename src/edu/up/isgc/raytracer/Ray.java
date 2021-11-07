/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer;

/**
 *
 * @author Jafet Rodríguez
 */

/**
 * Class Ray generates a ray with origin and direction, it is used to detect intersections and calculate
 */
public class Ray {

    private Vector3D origin;
    private Vector3D direction;

    /**
     * Class constructor
     * @param origin
     * @param direction
     */
    public Ray(Vector3D origin, Vector3D direction) {
        setOrigin(origin);
        setDirection(direction);
    }
    /**
     * Set and get methods to access class attributes
     */

    public Vector3D getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3D origin) {
        this.origin = origin;
    }

    public Vector3D getDirection() {
        return Vector3D.normalize(direction);
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }
}
