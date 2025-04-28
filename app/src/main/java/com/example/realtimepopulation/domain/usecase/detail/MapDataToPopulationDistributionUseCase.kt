package com.example.realtimepopulation.domain.usecase.detail

import com.example.realtimepopulation.domain.model.detail.AgeDistributionChartUiModel
import com.example.realtimepopulation.domain.model.detail.GenderDistribtuionChartUiModel
import com.example.realtimepopulation.domain.model.detail.PopulationDistributionData
import com.example.realtimepopulation.domain.model.map.MapData
import javax.inject.Inject

class MapDataToPopulationDistributionUseCase @Inject constructor(){
    operator fun invoke(detailScreenData: MapData): PopulationDistributionData {
        return PopulationDistributionData(
            genderDistributionChartUiModel = GenderDistribtuionChartUiModel(
                male = detailScreenData.maleRate,
                female = detailScreenData.femaleRate
            ),
            ageDistributionChartUiModel = AgeDistributionChartUiModel(
                populationRate0s = detailScreenData.populationRate0s,
                populationRate10s = detailScreenData.populationRate10s,
                populationRate20s = detailScreenData.populationRate20s,
                populationRate30s = detailScreenData.populationRate30s,
                populationRate40s = detailScreenData.populationRate40s,
                populationRate50s = detailScreenData.populationRate50s,
                populationRate60s = detailScreenData.populationRate60s,
                populationRate70s = detailScreenData.populationRate70s
            )
        )
    }
}