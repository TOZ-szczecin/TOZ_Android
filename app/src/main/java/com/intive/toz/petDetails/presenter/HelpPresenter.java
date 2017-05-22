package com.intive.toz.petDetails.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.intive.toz.info.model.Info;
import com.intive.toz.network.PetsApi;
import com.intive.toz.info.model.Help;
import com.intive.toz.petDetails.view.HelpPetMvp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Presenter for Pet Help fragment.
 */
public class HelpPresenter extends MvpBasePresenter<HelpPetMvp.HelpPetView> implements HelpPetMvp.Presenter {

    /**
     * load financial data from server.
     *
     * @param financialService service.
     */
    public void loadFinancialData(final PetsApi financialService) {
        getView().showProgress();
        Call<Info> call = financialService.getFinancialInfo();
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(final Call<Info> call, final Response<Info> response) {
                if (response.isSuccessful()) {
                    getView().setFinancialData(response.body());
                    getView().hideProgress();
                }
            }

            @Override
            public void onFailure(final Call<Info> call, final Throwable t) {
                getView().hideProgress();
                getView().showError(t);
            }
        });
    }

    @Override
    public void loadHowToDonateData(final PetsApi donateService) {
        getView().showProgress();
        Call<Help> call = donateService.getDonateInfo();
        call.enqueue(new Callback<Help>() {
            @Override
            public void onResponse(final Call<Help> call, final Response<Help> response) {
                if (response.isSuccessful()) {
                    getView().setDonateInfo(response.body());
                    getView().hideProgress();
                }
            }

            @Override
            public void onFailure(final Call<Help> call, final Throwable t) {
                getView().hideProgress();
                getView().showError(t);
            }
        });
    }
}
