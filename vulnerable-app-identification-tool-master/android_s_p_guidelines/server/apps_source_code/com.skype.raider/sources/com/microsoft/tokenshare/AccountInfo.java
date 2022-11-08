package com.microsoft.tokenshare;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import java.io.Serializable;
import java.util.Date;

public class AccountInfo implements Parcelable, Serializable {
    public static final Creator<AccountInfo> CREATOR = new Creator<AccountInfo>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AccountInfo[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            int dataPosition = parcel.dataPosition();
            if (AccountInfo.SERIALIZABLE_VALUE_CODE_NAME.equalsIgnoreCase(parcel.readString())) {
                return (AccountInfo) parcel.readSerializable();
            }
            parcel.setDataPosition(dataPosition);
            return new AccountInfo(parcel);
        }
    };
    public static final String SERIALIZABLE_VALUE_CODE_NAME = "readSerializable2";
    public static final String VERSION_KEY = "version";
    private static final long serialVersionUID = 1;
    private final String mAccountId;
    private final AccountType mAccountType;
    private final boolean mIsIntOrPpe;
    private String mParcelableVersion;
    private final String mPhoneNumber;
    private final String mPrimaryEmail;
    private String mProviderPackageId;
    private final Date mRefreshTokenAcquireTime;

    public enum AccountType {
        MSA,
        ORGID,
        OTHER
    }

    public String getPrimaryEmail() {
        return this.mPrimaryEmail;
    }

    public String getAccountId() {
        return this.mAccountId;
    }

    public AccountType getAccountType() {
        return this.mAccountType;
    }

    public boolean isIntOrPpe() {
        return this.mIsIntOrPpe;
    }

    public String getProviderPackageId() {
        return this.mProviderPackageId;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public Date getRefreshTokenAcquireTime() {
        return this.mRefreshTokenAcquireTime;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (SERIALIZABLE_VALUE_CODE_NAME.equalsIgnoreCase(this.mParcelableVersion)) {
            dest.writeString(this.mParcelableVersion);
            dest.writeSerializable(this);
            return;
        }
        dest.writeString(this.mAccountId);
        dest.writeString(this.mPrimaryEmail);
        dest.writeString(this.mAccountType.name());
        dest.writeByte((byte) (this.mIsIntOrPpe ? 1 : 0));
    }

    public AccountInfo(@NonNull String accountId, String primaryEmail, AccountType accountType, boolean isIntOrPpe, String phoneNumber, @NonNull Date refreshTokenAcquireTime) {
        this.mAccountId = accountId;
        this.mPrimaryEmail = primaryEmail;
        this.mAccountType = accountType;
        this.mIsIntOrPpe = isIntOrPpe;
        this.mPhoneNumber = phoneNumber;
        this.mRefreshTokenAcquireTime = refreshTokenAcquireTime;
        this.mParcelableVersion = SERIALIZABLE_VALUE_CODE_NAME;
    }

    protected AccountInfo(Parcel in) {
        this.mAccountId = in.readString();
        this.mPrimaryEmail = in.readString();
        this.mAccountType = AccountType.valueOf(in.readString());
        this.mIsIntOrPpe = in.readByte() != (byte) 0;
        this.mPhoneNumber = null;
        this.mRefreshTokenAcquireTime = null;
        this.mParcelableVersion = null;
    }

    public String toString() {
        return "AccountInfo{mAccountId='" + this.mAccountId + '\'' + ", mPrimaryEmail='" + this.mPrimaryEmail + '\'' + ", mAccountType='" + this.mAccountType.name() + '\'' + ", mIsIntOrPpe='" + this.mIsIntOrPpe + '\'' + ", mProviderPackageId='" + this.mProviderPackageId + '\'' + ", mPhoneNumber='" + this.mPhoneNumber + '\'' + ", mRefreshTokenAcquireTime='" + this.mRefreshTokenAcquireTime + '\'' + '}';
    }

    void setProviderPackageId(String packageId) {
        this.mProviderPackageId = packageId;
    }

    void setParcelableVersion(String version) {
        this.mParcelableVersion = version;
    }
}
