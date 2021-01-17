public class Planet{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV,
				double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
		// System.out.println("The first constructor initialized 6 instance variables.");
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
		// System.out.println("The second constructor initialized an identical Planet object");
	}

	public double calcDistance (Planet p){
		if (this == p){
			return 0.0000001;
		}
		else {
			double dx = p.xxPos - this.xxPos;
			double dy = p.yyPos - this.yyPos;
			return Math.sqrt(dx * dx + dy * dy);
		}
	}

	public double calcForceExertedBy (Planet p){
		if(this == p){
			return 0.0;
		}
		else {
			double distance = this.calcDistance(p);
			double force = (6.67 * Math.pow(10, -11) * this.mass * p.mass) / (distance * distance);
			return force;
		}
	}

	public double calcForceExertedByX(Planet p){
		double totalForce = this.calcForceExertedBy(p);
		double xForce = (totalForce * (p.xxPos - this.xxPos)) / this.calcDistance(p);
		return xForce;
	}

	public double calcForceExertedByY(Planet p){
		double totalForce = this.calcForceExertedBy(p);
		double yForce = (totalForce * (p.yyPos - this.yyPos)) / this.calcDistance(p);
		return yForce;
	}

	public double calcNetForceExertedByX(Planet[] planets){
		double xNetForce = 0;
		for (int i = 0; i < planets.length; i++){
			xNetForce = xNetForce + this.calcForceExertedByX(planets[i]);
		}
		return xNetForce;
	}

	public double calcNetForceExertedByY(Planet[] planets){
		double yNetForce = 0;
		for (int i = 0; i < planets.length; i++){
			yNetForce = yNetForce + this.calcForceExertedByY(planets[i]);
		}
		return yNetForce;
	}

	public Planet update(double time, double xForce, double yForce){
		double xAcceleration = xForce / this.mass;
		double yAcceleration = yForce / this.mass;
		this.xxVel = this.xxVel + xAcceleration * time;
		this.yyVel = this.yyVel + yAcceleration * time;
		this.xxPos = this.xxPos + xxVel * time;
		this.yyPos = this.yyPos + yyVel * time;

		return this;
	}

	public static void draw(Planet[] planets){
		for(int i = 0; i < planets.length; i++){
			StdDraw.picture(planets[i].xxPos, planets[i].yyPos, "images/"+planets[i].imgFileName);
		}
	}
}

