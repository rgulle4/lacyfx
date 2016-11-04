package cm.models;

import java.util.Set;

import static cm.models.Model.*;


/**
 * Created by Administrator on 2016/10/3.
 * This is meant for a whole design_temp (and also each layer_temp).
 */
public final class EnvPerformanceCalc {
    //Initialization
    //Conversion Factor
    double ConvF;
    //Weights
    double W_ENP = WEIGHTS.getwEnvPerformance();
    double W_GWP = WEIGHTS.getwGwp();
    double W_ODP = WEIGHTS.getwOdp();
    double W_AP = WEIGHTS.getwAp();
    double W_EP = WEIGHTS.getwEp();
    double W_POCP = WEIGHTS.getwPocp();
    double W_TWC = WEIGHTS.getwTotalWaterConsumption();
    double W_TPEC = WEIGHTS.getwTotalPrimaryEnergyConsumption();

    //Substance
    double Sub_GWP = TRANSPORTATION_PARAMETERS.getSub_GWP();
    double Sub_ODP = TRANSPORTATION_PARAMETERS.getSub_ODP();
    double Sub_AP = TRANSPORTATION_PARAMETERS.getSub_AP();
    double Sub_EP = TRANSPORTATION_PARAMETERS.getSub_EP();
    double Sub_POCP = TRANSPORTATION_PARAMETERS.getSub_POCP();
    double Sub_TW = TRANSPORTATION_PARAMETERS.getSub_TW();       //Total water consumption
    double Sub_TPEC = TRANSPORTATION_PARAMETERS.getSub_TPEC();   //Total Primary Energy Consumption


    // Normalization values
    double Norm_GWP = NORMALIZATIONS.getNorm_GWP();
    double Norm_ODP = NORMALIZATIONS.getNorm_ODP();
    double Norm_AP = NORMALIZATIONS.getNorm_AP();
    double Norm_EP = NORMALIZATIONS.getNorm_EP();
    double Norm_POCP = NORMALIZATIONS.getNorm_POCP();
    double Norm_TW = NORMALIZATIONS.getNorm_water();
    double Norm_TPEC = NORMALIZATIONS.getNorm_fuel();

    //EDP values
    double GWP_EDP;
    double ODP_EDP;
    double AP_EDP;
    double EP_EDP;
    double POCP_EDP;
    double TWC_EDP;
    double TPEC_EDP;
    double distance;

    //Environmental Calculation
    double GWP_TSP;
    double ODP_TSP;
    double AP_TSP;
    double EP_TSP;
    double POCP_TSP;
    double TWC_TSP;
    double TPEC_TSP;

    //distribute each factor's contribution from EPD
    double GWP_EDP_Ctb;
    double ODP_EDP_Ctb;
    double AP_EDP_Ctb;
    double EP_EDP_Ctb;
    double POCP_EDP_Ctb;
    double TotalWater_EDP_Ctb;
    double PrimaryTotalEnergyConsumption_EDP_Ctb;

    // distribute each factor's contribution from Transportation part
    double GWP_TSP_Ctb;
    double ODP_TSP_Ctb;
    double AP_TSP_Ctb;
    double EP_TSP_Ctb;
    double POCP_TSP_Ctb;
    double TotalWater_TSP_Ctb;
    double PrimaryTotalEnergyConsumption_TSP_Ctb;

    // Normalized_EDP raw values
    double GWP_EPD_NORM;
    double ODP_EPD_NORM;
    double AP_EPD_NORM;
    double EP_EPD_NORM;
    double POCP_EPD_NORM;
    double TotalWater_EPD_NORM;
    double PrimaryTotalEnergyConsumption_EDP_NORM;

    // Normalized_TSP raw values
    double GWP_TSP_NORM;
    double ODP_TSP_NORM;
    double AP_TSP_NORM;
    double EP_TSP_NORM;
    double POCP_TSP_NORM;
    double TotalWater_TSP_NORM;
    double PrimaryTotalEnergyConsumption_TSP_NORM;

    // SubScore_EPD
    double GWP_EPD_SubScore;
    double ODP_EPD_SubScore;
    double AP_EPD_SubScore;
    double EP_EPD_SubScore;
    double POCP_EPD_SubScore;
    double TotalWater_EPD_SubScore;
    double PrimaryTotalEnergyConsumption_EDP_SubScore;
    // SubScore_TSP
    double GWP_TSP_SubScore;
    double ODP_TSP_SubScore;
    double AP_TSP_SubScore;
    double EP_TSP_SubScore;
    double POCP_TSP_SubScore;
    double TotalWater_TSP_SubScore;
    double PrimaryTotalEnergyConsumption_TSP_SubScore;


    // Score for this layer

    double envPerfScore_layer;
    double envPerfScore_EDP_layer;
    double enPerfScore_Transportation_layer;
    double envPerfScore_Design;
    double envPerfScore_EDP_Design;
    double enPerfScore_Transportation_Design;

    Design design_temp;
    Layer layer_temp;
    Mix mix_temp;
    public void EnvAnalysisCalc(){

        Set<String> design_keys = DESIGNS.keySet();

        for (String key_i:design_keys) {
            //abstract some value from design_temp table
            design_temp = DESIGNS.get(key_i);
            for (int index_j = 0; index_j < design_temp.getNumberOfLayers(); index_j++) {
                layer_temp = design_temp.getLayer(index_j);
                // get material for this layer
                double unitConvFactor = 0.0;
                for (int index_k = 0; index_k < layer_temp.getNumberofMaterials(); index_k++) {
                    mix_temp = layer_temp.getMaterial(index_k);

                    // Environmental part
                    // set/ get UnitConversion_Factor
                    mix_temp.setUnitConversion_Factor();
                    unitConvFactor = mix_temp.getUnitConversion_Factor();
                    //get EPD value for this layer
                    GWP_EDP = mix_temp.getGWP();
                    ODP_EDP = mix_temp.getODP();
                    AP_EDP = mix_temp.getAP();
                    EP_EDP = mix_temp.getEP();
                    POCP_EDP = mix_temp.getPOCP();
                    TWC_EDP = mix_temp.getTotalWaterConsumption();
                    TPEC_EDP = mix_temp.getTotalPrimaryEnergyConsumption();

                    /* get EPD raw values by multiplying Conversion Factor
                    / Conversion Factor = unitConvFactor* layer's Volume
                    */
                    ConvF = layer_temp.getVolume() * unitConvFactor;

                    GWP_EDP_Ctb = ConvF * GWP_EDP;
                    ODP_EDP_Ctb = ConvF * ODP_EDP;
                    AP_EDP_Ctb = ConvF * AP_EDP;
                    EP_EDP_Ctb = ConvF * EP_EDP;
                    POCP_EDP_Ctb = ConvF * POCP_EDP;
                    TotalWater_EDP_Ctb = ConvF * TWC_EDP;
                    PrimaryTotalEnergyConsumption_EDP_Ctb = ConvF * TPEC_EDP;

                    //save distributed contribution in details for each layer_temp
                    //EPD part
                    mix_temp.setGWP_EDP_Ctb(GWP_EDP_Ctb);
                    mix_temp.setODP_EDP_Ctb(ODP_EDP_Ctb);
                    mix_temp.setAP_EDP_Ctb(AP_EDP_Ctb);
                    mix_temp.setEP_EDP_Ctb(EP_EDP_Ctb);
                    mix_temp.setPOCP_EDP_Ctb(POCP_EDP_Ctb);
                    mix_temp.setTW_EDP_Ctb(TWC_EDP);       //Total Water Consumption
                    mix_temp.setTPEC_EDP_Ctb(TPEC_EDP);    //Total Primary Energy Consumption


                        /* get and save normalized EPD values
                        */

                    GWP_EPD_NORM = GWP_EDP_Ctb / Norm_GWP;
                    ODP_EPD_NORM = ODP_EDP_Ctb / Norm_ODP;
                    AP_EPD_NORM = AP_EDP_Ctb / Norm_AP;
                    EP_EPD_NORM = EP_EDP_Ctb / Norm_EP;
                    POCP_EPD_NORM = EP_EDP_Ctb / Norm_POCP;
                    TotalWater_EPD_NORM = TotalWater_EDP_Ctb / Norm_TW;
                    PrimaryTotalEnergyConsumption_EDP_NORM
                            = PrimaryTotalEnergyConsumption_EDP_Ctb / Norm_TPEC;

                    mix_temp.setGWP_EDP_NORM(GWP_EPD_NORM);
                    mix_temp.setODP_EDP_NORM(ODP_EPD_NORM);
                    mix_temp.setAP_EDP_NORM(AP_EPD_NORM);
                    mix_temp.setEP_EDP_NORM(EP_EPD_NORM);
                    mix_temp.setPOCP_EDP_NORM(POCP_EPD_NORM);
                    mix_temp.setTW_EDP_NORM(TotalWater_EPD_NORM);
                    mix_temp.setTPEC_EDP_NORM(PrimaryTotalEnergyConsumption_EDP_NORM);

                        /* get and save EPD_SubScore values
                        formula: EPDscore =EPD_NORM * WEIGHT  */
                    GWP_EPD_SubScore = GWP_EPD_NORM * W_GWP;
                    ODP_EPD_SubScore = ODP_EPD_NORM * W_ODP;
                    AP_EPD_SubScore = AP_EPD_NORM * W_AP;
                    EP_EPD_SubScore = EP_EPD_NORM * W_EP;
                    POCP_EPD_SubScore = POCP_EPD_NORM * W_POCP;
                    TotalWater_EPD_SubScore = TotalWater_EPD_NORM * W_TWC;
                    PrimaryTotalEnergyConsumption_EDP_SubScore
                            = PrimaryTotalEnergyConsumption_EDP_NORM * W_TPEC;

                    mix_temp.setGWP_EDP_Subsore(GWP_EPD_SubScore);
                    mix_temp.setODP_EDP_Subsore(ODP_EPD_SubScore);
                    mix_temp.setAP_EDP_Subsore(AP_EPD_SubScore);
                    mix_temp.setEP_EDP_Subsore(EP_EPD_SubScore);
                    mix_temp.setPOCP_EDP_Subsore(POCP_EPD_SubScore);
                    mix_temp.setTW_EDP_Subsore(TotalWater_EPD_SubScore);
                    mix_temp.setTPEC_EDP_Subsore(PrimaryTotalEnergyConsumption_EDP_SubScore);

                    //Transportation part
                    //get Substance value
                    GWP_TSP = TRANSPORTATION_PARAMETERS.getSub_GWP();
                    ODP_TSP = TRANSPORTATION_PARAMETERS.getSub_ODP();
                    AP_TSP = TRANSPORTATION_PARAMETERS.getSub_AP();
                    EP_TSP = TRANSPORTATION_PARAMETERS.getSub_EP();
                    POCP_TSP = TRANSPORTATION_PARAMETERS.getSub_POCP();
                    TWC_TSP = TRANSPORTATION_PARAMETERS.getSub_TW();
                    TPEC_TSP = TRANSPORTATION_PARAMETERS.getSub_TPEC();

                    /* Calculate TSP_ctb values by use formula:
                        / TSP_ctb = 2*Substance*distance
                        */
                    //get distance
                    distance = TRANSPORTATION_PARAMETERS.getDistance();
                    //calculate TSP_ctb and save it
                    GWP_TSP_Ctb = GWP_TSP * distance * 2;
                    ODP_TSP_Ctb = ODP_TSP * distance * 2;
                    AP_TSP_Ctb = AP_TSP * distance * 2;
                    EP_TSP_Ctb = EP_TSP * distance * 2;
                    POCP_TSP_Ctb = POCP_TSP * distance * 2;
                    TotalWater_TSP_Ctb = TWC_TSP * distance * 2;
                    PrimaryTotalEnergyConsumption_TSP_Ctb = TPEC_TSP * distance * 2;

                    mix_temp.setGWP_Transportation_Ctb(GWP_TSP_Ctb);
                    mix_temp.setODP_Transportation_Ctb(ODP_TSP_Ctb);
                    mix_temp.setAP_Transportation_Ctb(AP_TSP_Ctb);
                    mix_temp.setEP_Transportation_Ctb(EP_TSP_Ctb);
                    mix_temp.setPOCP_Transportation_Ctb(POCP_TSP_Ctb);
                    mix_temp.setTW_Transportation_Ctb(TotalWater_TSP_Ctb);
                    mix_temp.setTPEC_Transportation_Ctb(PrimaryTotalEnergyConsumption_TSP_Ctb);

                    // get and save TSP_NORM
                    GWP_TSP_NORM = GWP_TSP_Ctb / Norm_GWP;
                    ODP_TSP_NORM = ODP_TSP_Ctb / Norm_ODP;
                    AP_TSP_NORM = AP_TSP_Ctb / Norm_AP;
                    EP_TSP_NORM = EP_TSP_Ctb / Norm_EP;
                    POCP_TSP_NORM = EP_TSP_Ctb / Norm_POCP;
                    TotalWater_TSP_NORM = TotalWater_TSP_Ctb / Norm_TW;
                    PrimaryTotalEnergyConsumption_TSP_NORM
                            = PrimaryTotalEnergyConsumption_TSP_Ctb / Norm_TPEC;

                    mix_temp.setGWP_Transportation_NORM(GWP_TSP_NORM);
                    mix_temp.setODP_Transportation_NORM(ODP_TSP_NORM);
                    mix_temp.setAP_Transportation_NORM(AP_TSP_NORM);
                    mix_temp.setEP_Transportation_NORM(EP_TSP_NORM);
                    mix_temp.setPOCP_Transportation_NORM(POCP_TSP_NORM);
                    mix_temp.setTW_Transportation_NORM(TotalWater_TSP_NORM);
                    mix_temp.setTPEC_Transportation_NORM(PrimaryTotalEnergyConsumption_TSP_NORM);

                    // get and save TSP_SubScore
                    GWP_TSP_SubScore = GWP_TSP_NORM * W_GWP;
                    ODP_TSP_SubScore = ODP_TSP_NORM * W_ODP;
                    AP_TSP_SubScore = AP_TSP_NORM * W_AP;
                    EP_TSP_SubScore = EP_TSP_NORM * W_EP;
                    POCP_TSP_SubScore = POCP_TSP_NORM * W_POCP;
                    TotalWater_TSP_SubScore = TotalWater_TSP_NORM * W_TWC;
                    PrimaryTotalEnergyConsumption_TSP_SubScore
                            = PrimaryTotalEnergyConsumption_TSP_NORM * W_TPEC;

                    mix_temp.setGWP_Transportation_Subsore(GWP_TSP_SubScore);
                    mix_temp.setODP_Transportation_Subsore(ODP_TSP_SubScore);
                    mix_temp.setAP_Transportation_Subsore(AP_TSP_SubScore);
                    mix_temp.setEP_Transportation_Subsore(EP_TSP_SubScore);
                    mix_temp.setPOCP_Transportation_Subsore(POCP_TSP_SubScore);
                    mix_temp.setTW_Transportation_Subsore(TotalWater_TSP_SubScore);
                    mix_temp.setTPEC_Transportation_Subsore(PrimaryTotalEnergyConsumption_TSP_SubScore);


                    // EPD_Score and TSP_Score for this layer
                    envPerfScore_EDP_layer = GWP_EPD_SubScore
                            + ODP_EPD_SubScore
                            + AP_EPD_SubScore
                            + EP_EPD_SubScore
                            + POCP_EPD_SubScore
                            + TotalWater_EPD_SubScore
                            + PrimaryTotalEnergyConsumption_EDP_SubScore;

                    enPerfScore_Transportation_layer = GWP_TSP_SubScore
                            + ODP_TSP_SubScore
                            + AP_TSP_SubScore
                            + EP_TSP_SubScore
                            + POCP_TSP_SubScore
                            + TotalWater_TSP_SubScore
                            + PrimaryTotalEnergyConsumption_TSP_SubScore;
                    // Final Score for this layer
                    envPerfScore_layer = envPerfScore_EDP_layer + enPerfScore_Transportation_layer;

                    //save layer_temp's score
                    mix_temp.setEnvPerfAnalysis_TotalScore_Material(envPerfScore_layer);
                    mix_temp.setEnvPerfAnalysis_EPDScore_Material(envPerfScore_EDP_layer);
                    mix_temp.setEnvPerfAnalysis_TransportationScore_Material(enPerfScore_Transportation_layer);
                }
                //accumulate Design_temp's score
                //Calculate design_temp's score
                envPerfScore_Design = envPerfScore_Design + envPerfScore_layer;
                envPerfScore_EDP_Design = envPerfScore_EDP_Design + envPerfScore_EDP_layer;
                enPerfScore_Transportation_Design = enPerfScore_Transportation_Design +enPerfScore_Transportation_layer;
            }
        //save design_temp's score;
        design_temp.setEnvPerfAnalysis_TotalScore_Design(envPerfScore_Design);
        design_temp.setEnvPerfAnalysis_EPDScore_Design(envPerfScore_EDP_Design);
        design_temp.setEnvPerfAnalysis_TransportationScore_Design(enPerfScore_Transportation_Design);
        //save design
        DESIGNS.put(key_i, design_temp);
    }




}

    public void Calc_layer(){

    }

    public void Calc_design(){

    }
}
