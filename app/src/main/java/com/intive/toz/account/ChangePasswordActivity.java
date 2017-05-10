package com.intive.toz.account;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.widget.Button;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.intive.toz.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChangePasswordActivity extends MvpActivity<ChangePasswordMVP.ChangePasswordView,
        ChangePasswordPresenter> implements ChangePasswordMVP.ChangePasswordView {

    @BindView(R.id.input_old_password)
    TextInputEditText oldPassword;

    @BindView(R.id.input_new_password)
    TextInputEditText newPassword;

    @BindView(R.id.input_repeat_new_password)
    TextInputEditText repeatNewPassword;

    @BindView(R.id.confirm_change_password)
    Button changePassword;

    @BindView(R.id.cancel_change_password)
    Button cancelButton;

    @NonNull
    @Override
    public ChangePasswordPresenter createPresenter() {
        return new ChangePasswordPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.confirm_change_password)
    public void onChangePasswordClicked() {
        presenter.validatePassword(oldPassword.getText().toString(),
                                    newPassword.getText().toString(),
                                    repeatNewPassword.getText().toString());
    }

    @OnClick(R.id.cancel_change_password)
    public void onCancelButtonClicked() {
        finish();
    }

    @Override
    public void showEmptyOldPasswordError() {
        oldPassword.setError(getString(R.string.empty_field_error));
    }

    @Override
    public void showEmptyNewPasswordError() {
        newPassword.setError(getString(R.string.empty_field_error));
    }

    @Override
    public void showEmptyRepeatedNewPasswordError() {
        repeatNewPassword.setError(getString(R.string.empty_field_error));
    }

    @Override
    public void showDifferentNewPasswordsError() {
        newPassword.setError(getString(R.string.different_new_passwords_error));
        repeatNewPassword.setError(getString(R.string.different_new_passwords_error));
    }

    @Override
    public void hideDifferentNewPasswordsError() {
        newPassword.setError(null);
        repeatNewPassword.setError(null);
    }

    @Override
    public void showWrongPasswordError() {
        oldPassword.setError(getString(R.string.wrong_password_error));
    }

    @Override
    public void showTooLongPasswordError() {
        newPassword.setError(getString(R.string.too_long_password_error));
    }
}
