package cm.models;

/**
 * Created by Administrator on 2016/10/2.
 */
public class Weights {

    // Weights for scoring Env + Econ for total score.
    private double wEnvPerformance;
    private double wEconPerformance;

    // Environmental impact weights (used by
    // both EPD and for Transportation).
    private double wGwp;
    private double wOdp;
    private double wAp;
    private double wEp;
    private double wPocp;
    private double wConcreteWaste;
    private double wConcreteHazWaste;    // "hidden" from user
    private double wConcreteNonHazWaste; // "hidden" from user
    private double wTotalWaterConsumption;
    private double wTotalPrimaryEnergyConsumption;
    private double wRenewableEnergyConsumption; // "hidden" from user
    private double wNonRenewableEnergyConsumption; // "hidden" from user
    private double wMaterialResourceConsumption;
    private double wRenewableMaterialResourceConsumption; // "hidden" from user
    private double wNonRenewableMaterialResourceConsumption; // "hidden" from user

    public double getwEnvPerformance() {
        return wEnvPerformance;
    }

    public Weights setwEnvPerformance(double wEnvPerformance) {
        this.wEnvPerformance = wEnvPerformance;
        return this;
    }

    public double getwEconPerformance() {
        return wEconPerformance;
    }

    public Weights setwEconPerformance(double wEconPerformance) {
        this.wEconPerformance = wEconPerformance;
        return this;
    }

    public double getwGwp() {
        return wGwp;
    }

    public Weights setwGwp(double wGwp) {
        this.wGwp = wGwp;
        return this;
    }

    public double getwOdp() {
        return wOdp;
    }

    public Weights setwOdp(double wOdp) {
        this.wOdp = wOdp;
        return this;
    }

    public double getwAp() {
        return wAp;
    }

    public Weights setwAp(double wAp) {
        this.wAp = wAp;
        return this;
    }

    public double getwEp() {
        return wEp;
    }

    public Weights setwEp(double wEp) {
        this.wEp = wEp;
        return this;
    }

    public double getwPocp() {
        return wPocp;
    }

    public Weights setwPocp(double wPocp) {
        this.wPocp = wPocp;
        return this;
    }

    public double getwConcreteHazWaste() {
        return wConcreteHazWaste;
    }

    public Weights setwConcreteHazWaste(double wConcreteHazWaste) {
        this.wConcreteHazWaste = wConcreteHazWaste;
        return this;
    }

    public double getwConcreteNonHazWaste() {
        return wConcreteNonHazWaste;
    }

    public Weights setwConcreteNonHazWaste(double wConcreteNonHazWaste) {
        this.wConcreteNonHazWaste = wConcreteNonHazWaste;
        return this;
    }

    public double getwTotalWaterConsumption() {
        return wTotalWaterConsumption;
    }

    public Weights setwTotalWaterConsumption(double wTotalWaterConsumption) {
        this.wTotalWaterConsumption = wTotalWaterConsumption;
        return this;
    }

    public double getwRenewableEnergyConsumption() {
        return wRenewableEnergyConsumption;
    }

    public Weights setwRenewableEnergyConsumption(double wRenewableEnergyConsumption) {
        this.wRenewableEnergyConsumption = wRenewableEnergyConsumption;
        return this;
    }

    public double getwNonRenewableEnergyConsumption() {
        return wNonRenewableEnergyConsumption;
    }

    public Weights setwNonRenewableEnergyConsumption(double wNonRenewableEnergyConsumption) {
        this.wNonRenewableEnergyConsumption = wNonRenewableEnergyConsumption;
        return this;
    }

    public double getwRenewableMaterialResourceConsumption() {
        return wRenewableMaterialResourceConsumption;
    }

    public Weights setwRenewableMaterialResourceConsumption(double wRenewableMaterialResourceConsumption) {
        this.wRenewableMaterialResourceConsumption = wRenewableMaterialResourceConsumption;
        return this;
    }

    public double getwNonRenewableMaterialResourceConsumption() {
        return wNonRenewableMaterialResourceConsumption;
    }

    public Weights setwNonRenewableMaterialResourceConsumption(double wNonRenewableMaterialResourceConsumption) {
        this.wNonRenewableMaterialResourceConsumption = wNonRenewableMaterialResourceConsumption;
        return this;
    }

    public double getwConcreteWaste() {
        return wConcreteWaste;
    }

    public Weights setwConcreteWaste(double wConcreteWaste) {
        this.wConcreteWaste = wConcreteWaste;
        return this;
    }

    public double getwTotalPrimaryEnergyConsumption() {
        return wTotalPrimaryEnergyConsumption;
    }

    public Weights setwTotalPrimaryEnergyConsumption(double wTotalPrimaryEnergyConsumption) {
        this.wTotalPrimaryEnergyConsumption = wTotalPrimaryEnergyConsumption;
        return this;
    }

    public double getwMaterialResourceConsumption() {
        return wMaterialResourceConsumption;
    }

    public Weights setwMaterialResourceConsumption(double wMaterialResourceConsumption) {
        this.wMaterialResourceConsumption = wMaterialResourceConsumption;
        return this;
    }
}
