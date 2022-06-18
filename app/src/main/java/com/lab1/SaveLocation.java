package com.lab1;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveLocation implements Parcelable {
    private String address;
    private double latitude;
    private double longitude;
    private int id;

    public SaveLocation(String address, double latitude, double longitude, int id) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
    }

    protected SaveLocation(Parcel in) {
        address = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        id = in.readInt();
    }

    public static final Creator<SaveLocation> CREATOR = new Creator<SaveLocation>() {
        @Override
        public SaveLocation createFromParcel(Parcel in) {
            return new SaveLocation(in);
        }

        @Override
        public SaveLocation[] newArray(int size) {
            return new SaveLocation[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeInt(id);
    }
}
