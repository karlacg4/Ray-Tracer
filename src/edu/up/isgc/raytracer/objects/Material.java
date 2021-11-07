package edu.up.isgc.raytracer.objects;

/**
 * @Author Karla Carpinteyro
 * Class Material
 * This class is used to create different types of materials changing the values of shininess, reflection and refraction
 */


public class Material {

    private double shininess;
    private double ambient;
    private boolean isReflective;
    private double reflectiveness;
    private double refractiveIndex;

    /**
     * Set and get methods to access class attributes
     */

    public double getShininess() {
        return shininess;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public double getAmbient() {
        return ambient;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public boolean isReflective() {
        return isReflective;
    }

    public void setReflective(boolean reflective) {
        isReflective = reflective;
    }

    public double getReflectiveness() {
        return reflectiveness;
    }

    public void setReflectiveness(double reflectiveness) {
        this.reflectiveness = reflectiveness;
    }

    public double getRefractiveIndex() {
        return refractiveIndex;
    }

    public void setRefractiveIndex(double refractiveIndex) {
        this.refractiveIndex = refractiveIndex;
    }

    /**
     * Class constructor
     * @param shininess double
     * @param ambient double
     * @param isReflective boolean
     * @param reflectiveness double
     * @param refractiveIndex double
     */
    public Material(double shininess, double ambient, boolean isReflective, double reflectiveness, double refractiveIndex){
        setAmbient(ambient);
        setShininess(shininess);
        setReflective(isReflective);
        setReflectiveness(reflectiveness);
        setRefractiveIndex(refractiveIndex);
    }



}
