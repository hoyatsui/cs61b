public class NBody {
    public static double readRadius(String file_name) {
        In in = new In(file_name);
        @Deprecated
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String file_name, int lines) {
        In in = new In(file_name);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[lines];

        for (int i = 0; i < lines; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                                    in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        String background_file = "images/starfield.jpg";
        int NumofPlanets = 5;
        int strat_time = 0;

        double[] xForces = new double[NumofPlanets];
        double[] yForces = new double[NumofPlanets];

        Planet[] planets = readPlanets(filename, NumofPlanets);
        StdDraw.setScale(-2.50e+11, 2.50e+11);
        StdDraw.enableDoubleBuffering();
        while(strat_time < T) {
            for(int j = 0; j < NumofPlanets; j++){
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }
            for(int j = 0; j < NumofPlanets; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.clear();
            StdDraw.picture(0,0, background_file);
            Planet.draw(planets);
            StdDraw.show();
            StdDraw.pause(10);
            strat_time += dt;
        }

        double radius = readRadius(filename);
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

}