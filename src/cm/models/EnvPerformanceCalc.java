package cm.models;

import java.util.Set;

import static cm.App.*;


/**
 * Created by Administrator on 2016/10/3.
 */
public class EnvPerformanceCalc {
    Design design;
    Layer layer;
    Material material;
    Weights weight;
    TransportationParameters transportationParameters;


    Set<String> design_keys = designMap.keySet();
    Set<String> layer_keys = layerMap.keySet();
    Set<String> material_keys = materialMap.keySet();
    Set<String> weights_keys = weightsMap.keySet();
    Set<String> transportationPara_keys = transportationParametersMap.keySet();



    //Initialization
    //Conversion Factor
    double ConvF;
    //Weights
    double W_ENP;
    double W_GWP;
    double W_ODP;
    double W_AP;
    double W_EP;
    double W_POCP;
    double W_TWC;
    double W_TPEC;

    //Substance
    double Sub_GWP;
    double Sub_ODP;
    double Sub_AP;
    double Sub_EP;
    double Sub_POCP;
    double Sub_TW;     //Total water consumption
    double Sub_TPEC;   //Total Primary Energy Consumption

    // Normalization values
    Normalization normalization = new Normalization();
    double Norm_GWP = normalization.getNorm_GWP();
    double Norm_ODP = normalization.getNorm_ODP();
    double Norm_AP = normalization.getNorm_AP();
    double Norm_EP = normalization.getNorm_EP();
    double Norm_POCP = normalization.getNorm_POCP();
    double Norm_TW = normalization.getNorm_water();
    double Norm_TPEC = normalization.getNorm_fuel();

    //EDP values
    double GWP_EDP;
    double ODP_EDP;
    double AP_EDP;
    double EP_EDP;
    double POCP_EDP;
    double TWC_EDP;
    double TPEC_EDP;
    double Distance;

    //Environmental Calculation
    double GWP_TSP;
    double ODP_TSP;
    double AP_TSP;
    double EP_TSP;
    double POCP_TSP;
    double TWC_TSP;
    double PrimaryTotalEnergyConsumption_Transportation;

    //distribute each factor's contribution from EPD
    double GWP_EDP_Ctb;
    double ODP_EDP_Ctb;
    double AP_EDP_Ctb;
    double EP_EDP_Ctb;
    double POCP_EDP_Ctb;
    double TotalWater_EDP_Ctb;
    double PrimaryTotalEnergyConsumption_EDP_Ctb;

    //distribute each factor's contribution from Transportation
    double GWP_Transportation_Ctb;
    double ODP_Transportation_Ctb;
    double AP_Transportation_Ctb;
    double EP_Transportation_Ctb;
    double POCP_Transportation_Ctb;
    double TotalWater_Transportation_Ctb;
    double PrimaryTotalEnergyConsumption_Transportation_Ctb;
    double envPerfScore_layer;
    double envPerfScore_EDP_layer;
    double enPerfScore_Transportation_layer;
    double envPerfScore_Design;
    double envPerfScore_EDP_Design;
    double enPerfScore_Transportation_Design;

    public void EnvAnalysisCalc(){

        for (String key: weights_keys) {
            //abstract weights values from weight table
            weight = weightsMap.get(key);
            W_ENP = weight.getwEnvPerformance();
            W_GWP = weight.getwGwp();
            W_ODP = weight.getwOdp();
            W_AP = weight.getwAp();
            W_EP = weight.getwEp();
            W_POCP = weight.getwPocp();
            W_TWC = weight.getwTotalWaterConsumption();
            W_TPEC = weight.getwTotalPrimaryEnergyConsumption();
        }

        for (String key_t:transportationPara_keys){
            //abstract Substance values from TransportationParameters table;
            transportationParameters = transportationParametersMap.get(key_t);
             Sub_GWP = transportationParameters.getSub_GWP();
             Sub_ODP = transportationParameters.getSub_ODP();
             Sub_AP = transportationParameters.getSub_AP();
             Sub_EP = transportationParameters.getSub_EP();
             Sub_POCP = transportationParameters.getSub_POCP();
             Sub_TW = transportationParameters.getSub_TW();       //Total water consumption
             Sub_TPEC = transportationParameters.getSub_TPEC();   //Total Primary Energy Consumption

        }


        for (String key_i:design_keys) {
            //abstract some value from design table
            for (String key_j : layer_keys) {
                //abstract some value from layer table
                layer = layerMap.get(key_j);

                ConvF = layer.getVolume()/1.0;

                for(String key_k : material_keys){
                    //abstract some value from material table
                    material = materialMap.get(key_k);

                     GWP_EDP = material.getGWP();
                     ODP_EDP = material.getODP();
                     AP_EDP = material.getAP();
                     EP_EDP = material.getEP();
                     POCP_EDP = material.getPOCP();
                     TWC_EDP = material.getTotalWaterConsumption();
                     TPEC_EDP = material.getTotalPrimaryEnergyConsumption();
                     Distance = material.getDistance();

                    //Do Calculations
                    cal();

                    //save distributed contribution in details for each layer
                    //EPD part
                    layer.setGWP_EDP_Ctb(GWP_EDP_Ctb);
                    layer.setODP_EDP_Ctb(ODP_EDP_Ctb);
                    layer.setAP_EDP_Ctb(AP_EDP_Ctb);
                    layer.setEP_EDP_Ctb(EP_EDP_Ctb);
                    layer.setPOCP_EDP_Ctb(POCP_EDP_Ctb);
                    layer.setTW_EDP_Ctb(TWC_EDP);       //Total Water Consumption
                    layer.setTPEC_EDP_Ctb(TPEC_EDP);    //Total Primary Energy Consumption
                    //Transportation part
                    layer.setGWP_Transportation_Ctb(GWP_Transportation_Ctb);
                    layer.setODP_Transportation_Ctb(ODP_Transportation_Ctb);
                    layer.setAP_Transportation_Ctb(AP_Transportation_Ctb);
                    layer.setEP_Transportation_Ctb(EP_Transportation_Ctb);
                    layer.setPOCP_Transportation_Ctb(POCP_Transportation_Ctb);
                    layer.setTW_Transportation_Ctb(TotalWater_Transportation_Ctb);
                    layer.setTPEC_Transportation_Ctb(PrimaryTotalEnergyConsumption_Transportation_Ctb);
                    //save layer's score
                    layer.setEnvPerfAnalysis_TotalScore_Layer(envPerfScore_layer);
                    layer.setEnvPerfAnalysis_EPDScore_Layer(envPerfScore_EDP_layer);
                    layer.setEnvPerfAnalysis_TransportationScore_Layer(enPerfScore_Transportation_layer);

                    layerMap.put(key_j,layer);
                    //Calculate design's score
                    envPerfScore_Design = envPerfScore_Design + envPerfScore_layer;
                    envPerfScore_EDP_Design = envPerfScore_EDP_Design + envPerfScore_EDP_layer;
                    enPerfScore_Transportation_Design = enPerfScore_Transportation_Design +enPerfScore_Transportation_layer;
                }

            }
            //save design's score;
            design.setEnvPerfAnalysis_TotalScore_Design(envPerfScore_Design);
            design.setEnvPerfAnalysis_EPDScore_Design(envPerfScore_EDP_Design);
            design.setEnvPerfAnalysis_TransportationScore_Design(enPerfScore_Transportation_Design);
            designMap.put(key_i,design);
        }
    }

    public void cal(){

        //Environmental Calculation
        GWP_TSP = Sub_GWP*  Distance *2;
        ODP_TSP = Sub_ODP*  Distance *2;
        AP_TSP = Sub_AP*  Distance *2;
        EP_TSP = Sub_EP*  Distance *2;
        POCP_TSP = Sub_POCP* Distance *2;
        TWC_TSP = Sub_TW* Distance *2;
        PrimaryTotalEnergyConsumption_Transportation = Sub_TPEC* Distance*2;

        //distribute each factor's contribution from EPD
         GWP_EDP_Ctb=ConvF*W_ENP*W_GWP*GWP_EDP/ Norm_GWP;
         ODP_EDP_Ctb=ConvF*W_ENP*W_ODP*ODP_EDP/Norm_ODP;
         AP_EDP_Ctb=ConvF*W_ENP*W_AP*AP_EDP/Norm_AP;
         EP_EDP_Ctb=ConvF*W_ENP*W_EP*EP_EDP/Norm_EP;
         POCP_EDP_Ctb=ConvF*W_ENP*W_POCP*POCP_EDP/Norm_POCP;
         TotalWater_EDP_Ctb=ConvF*W_ENP*W_TWC*TWC_EDP/Norm_TW;
         PrimaryTotalEnergyConsumption_EDP_Ctb =ConvF*W_ENP* W_TPEC * TPEC_EDP /Norm_TPEC;

        //distribute each factor's contribution from Transportation
         GWP_Transportation_Ctb=W_ENP*W_GWP*GWP_TSP/Norm_GWP;
         ODP_Transportation_Ctb=W_ENP*W_ODP*ODP_TSP/Norm_ODP;
         AP_Transportation_Ctb=W_ENP*W_AP*AP_TSP/Norm_AP;
         EP_Transportation_Ctb=W_ENP*W_EP*EP_TSP/Norm_EP;
         POCP_Transportation_Ctb=W_ENP*W_POCP*POCP_TSP/Norm_POCP;
         TotalWater_Transportation_Ctb=W_ENP*W_TWC*TWC_TSP/Norm_TW;
         PrimaryTotalEnergyConsumption_Transportation_Ctb =W_ENP* W_TPEC * PrimaryTotalEnergyConsumption_Transportation /Norm_TPEC;

         envPerfScore_layer = GWP_EDP_Ctb+ODP_EDP_Ctb+AP_EDP_Ctb+EP_EDP_Ctb+POCP_EDP_Ctb+TotalWater_EDP_Ctb+ PrimaryTotalEnergyConsumption_EDP_Ctb
                +GWP_Transportation_Ctb+ODP_Transportation_Ctb+AP_Transportation_Ctb+EP_Transportation_Ctb+POCP_Transportation_Ctb
                +TotalWater_Transportation_Ctb+ PrimaryTotalEnergyConsumption_Transportation_Ctb;

         envPerfScore_EDP_layer = GWP_EDP_Ctb+ODP_EDP_Ctb+AP_EDP_Ctb+EP_EDP_Ctb+POCP_EDP_Ctb+TotalWater_EDP_Ctb+ PrimaryTotalEnergyConsumption_EDP_Ctb;

         enPerfScore_Transportation_layer = GWP_Transportation_Ctb+ODP_Transportation_Ctb+AP_Transportation_Ctb+EP_Transportation_Ctb+POCP_Transportation_Ctb
                 +TotalWater_Transportation_Ctb+ PrimaryTotalEnergyConsumption_Transportation_Ctb;

    }
}
