/**
 * [1968] - [2021] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer.objects;

import edu.up.isgc.raytracer.IIntersectable;
import edu.up.isgc.raytracer.Vector3D;

import java.awt.*;

/**
 *
 * @author Jafet Rodríguez
 */

/**
 * public abstract class Object3D implements IIntersectable
 * Object that can be intersected
 */
public abstract class Object3D implements IIntersectable {

    private Vector3D position;
    private Color color;
    private Material material;

    /**
     * Set and get methods to access class attributes
     */

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    //Overload constructor
    /**
     * Class constructor
     * @param position
     * @param color
     * @param material
     */
    public Object3D(Vector3D position, Color color, Material material) {
        setPosition(position);
        setColor(color);
        setMaterial(material);
    }

    /**
     * Class constructor
     * @param position
     * @param color
     */
    public Object3D(Vector3D position, Color color) {
        setPosition(position);
        setColor(color);
    }


}
