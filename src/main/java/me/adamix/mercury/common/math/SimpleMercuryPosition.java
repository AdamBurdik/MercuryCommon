package me.adamix.mercury.common.math;

import me.adamix.mercury.api.math.MercuryPosition;

public class SimpleMercuryPosition implements MercuryPosition {
	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;

	public SimpleMercuryPosition(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public SimpleMercuryPosition(double x, double y, double z, float yaw, float pitch) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	@Override
	public double x() {
		return x;
	}

	@Override
	public void x(double value) {
		this.x = value;
	}

	@Override
	public double y() {
		return y;
	}

	@Override
	public void y(double value) {
		this.y = value;;
	}

	@Override
	public double z() {
		return z;
	}

	@Override
	public void z(double value) {
		this.z = value;
	}

	@Override
	public float pitch() {
		return pitch;
	}

	@Override
	public void pitch(float value) {
		this.pitch = value;
	}

	@Override
	public float yaw() {
		return yaw;
	}

	@Override
	public void yaw(float value) {
		this.yaw = value;
	}
}
