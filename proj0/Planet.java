public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
				double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;

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
			return 0.0;
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
		double distance = 0;
		if(this == p){
			distance = 0.0001;
		}else {
			distance = this.calcDistance(p);
		}
		double xForce = (totalForce * (p.xxPos - this.xxPos)) / distance;
		return xForce;
	}

	public double calcForceExertedByY(Planet p){
		double totalForce = this.calcForceExertedBy(p);
		double distance = 0;
		if(this == p){
			distance = 0.0001;
		}else {
			distance = this.calcDistance(p);
		}
		double yForce = (totalForce * (p.yyPos - this.yyPos)) / distance;
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

	public void update(double time, double xForce, double yForce){
		double xAcceleration = xForce / mass;
		double yAcceleration = yForce / mass;
		xxVel = xxVel + xAcceleration * time;
		yyVel = yyVel + yAcceleration * time;
		xxPos = xxPos + xxVel * time;
		yyPos = yyPos + yyVel * time;

	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos,"images/"+ this.imgFileName);
	}
}

