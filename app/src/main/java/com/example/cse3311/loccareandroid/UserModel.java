package com.example.cse3311.loccareandroid;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class UserModel implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String password;
    private String roomCode;
    private double latitude = 0;
    private double longitude = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public LatLng getCurrentLocation() {
        Random rand = new Random();

        // Hard-coded locations for testing displaying a user on the map

        LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
        LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
        LatLng DARWIN = new LatLng(-12.4634, 130.8456);
        LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
        LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
        LatLng PERTH = new LatLng(-31.952854, 115.857342);
        LatLng ALICE_SPRINGS = new LatLng(-24.6980, 133.8807);

        LatLng[] locations = {
                BRISBANE,
                MELBOURNE,
                DARWIN,
                SYDNEY,
                ADELAIDE,
                PERTH,
                ALICE_SPRINGS
        };

        int randomLocationIndex = rand.nextInt(locations.length);
        return locations[randomLocationIndex];

        // For actual working code use the return value below
//        return new LatLng(latitude, longitude);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
