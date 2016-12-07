package fatfitandhealthy.dao;

// default package
// Generated 7 Dec, 2016 9:53:14 AM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserHealth generated by hbm2java
 */
@Entity
@Table(name = "user_health", catalog = "fatfitandhealthy")
public class UserHealth implements java.io.Serializable {

	private int id;
	private int uno;
	private String height;
	private String weight;
	private String activityFactor;
	private String weightGoal;
	private String kgs;
	private String dailyCalGoal;

	public UserHealth() {
	}

	public UserHealth(int id, int uno, String height, String weight, String activityFactor, String weightGoal,
			String kgs, String dailyCalGoal) {
		this.id = id;
		this.uno = uno;
		this.height = height;
		this.weight = weight;
		this.activityFactor = activityFactor;
		this.weightGoal = weightGoal;
		this.kgs = kgs;
		this.dailyCalGoal = dailyCalGoal;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "uno", nullable = false)
	public int getUno() {
		return this.uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	@Column(name = "height", nullable = false)
	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Column(name = "weight", nullable = false)
	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "activity_factor", nullable = false)
	public String getActivityFactor() {
		return this.activityFactor;
	}

	public void setActivityFactor(String activityFactor) {
		this.activityFactor = activityFactor;
	}

	@Column(name = "weight_goal", nullable = false)
	public String getWeightGoal() {
		return this.weightGoal;
	}

	public void setWeightGoal(String weightGoal) {
		this.weightGoal = weightGoal;
	}

	@Column(name = "kgs", nullable = false)
	public String getKgs() {
		return this.kgs;
	}

	public void setKgs(String kgs) {
		this.kgs = kgs;
	}

	@Column(name = "daily_cal_goal", nullable = false)
	public String getDailyCalGoal() {
		return this.dailyCalGoal;
	}

	public void setDailyCalGoal(String dailyCalGoal) {
		this.dailyCalGoal = dailyCalGoal;
	}

}