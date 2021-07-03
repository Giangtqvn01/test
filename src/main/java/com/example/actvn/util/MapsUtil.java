package com.example.actvn.util;

public class MapsUtil {
    public static double distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
        double R = 6371;
        double dLat = (la2 - la1) * (Math.PI / 180);
        double dLon = (lo2 - lo1) * (Math.PI / 180);
        double la1ToRad = la1 * (Math.PI / 180);
        double la2ToRad = la2 * (Math.PI / 180);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(la1ToRad)
                * Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }

    public static void main(String[] args) {
        double v = MapsUtil.distanceBetween2Points(21.0282686,105.783467,21.0282305,105.7837164);
        System.out.println(v);
    }
}
