package android.support.v7.app;

final class j {
    private static j d;
    public long a;
    public long b;
    public int c;

    j() {
    }

    static j a() {
        if (d == null) {
            d = new j();
        }
        return d;
    }

    public final void a(long time, double latitude, double longitude) {
        float daysSince2000 = ((float) (time - 946728000000L)) / 8.64E7f;
        float meanAnomaly = 6.24006f + (0.01720197f * daysSince2000);
        double solarLng = ((((((double) meanAnomaly) + (0.03341960161924362d * Math.sin((double) meanAnomaly))) + (3.4906598739326E-4d * Math.sin((double) (2.0f * meanAnomaly)))) + (5.236000106378924E-6d * Math.sin((double) (3.0f * meanAnomaly)))) + 1.796593063d) + 3.141592653589793d;
        double arcLongitude = (-longitude) / 360.0d;
        double solarTransitJ2000 = ((((double) (((float) Math.round(((double) (daysSince2000 - 9.0E-4f)) - arcLongitude)) + 9.0E-4f)) + arcLongitude) + (0.0053d * Math.sin((double) meanAnomaly))) + (-0.0069d * Math.sin(2.0d * solarLng));
        double solarDec = Math.asin(Math.sin(solarLng) * Math.sin(0.4092797040939331d));
        double latRad = latitude * 0.01745329238474369d;
        double cosHourAngle = (Math.sin(-0.10471975803375244d) - (Math.sin(latRad) * Math.sin(solarDec))) / (Math.cos(latRad) * Math.cos(solarDec));
        if (cosHourAngle >= 1.0d) {
            this.c = 1;
            this.a = -1;
            this.b = -1;
        } else if (cosHourAngle <= -1.0d) {
            this.c = 0;
            this.a = -1;
            this.b = -1;
        } else {
            float hourAngle = (float) (Math.acos(cosHourAngle) / 6.283185307179586d);
            this.a = Math.round((((double) hourAngle) + solarTransitJ2000) * 8.64E7d) + 946728000000L;
            this.b = Math.round((solarTransitJ2000 - ((double) hourAngle)) * 8.64E7d) + 946728000000L;
            if (this.b >= time || this.a <= time) {
                this.c = 1;
            } else {
                this.c = 0;
            }
        }
    }
}
