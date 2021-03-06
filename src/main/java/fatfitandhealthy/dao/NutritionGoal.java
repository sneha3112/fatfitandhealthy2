package fatfitandhealthy.dao;

// default package
// Generated 3 Jan, 2017 3:36:01 PM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NutritionGoal generated by hbm2java
 */
@Entity
@Table(name = "nutrition_goal", catalog = "fatfitandhealthy")
public class NutritionGoal implements java.io.Serializable {

	private Integer id;
	private float fat;
	private float cholesterol;
	private float sodium;
	private float potassium;
	private float carbohydrates;
	private float fiber;
	private float sugars;
	private float protein;
	private float water;

	public NutritionGoal() {
	}

	public NutritionGoal(float fat, float cholesterol, float sodium, float potassium, float carbohydrates, float fiber,
			float sugars, float protein, float water) {
		this.fat = fat;
		this.cholesterol = cholesterol;
		this.sodium = sodium;
		this.potassium = potassium;
		this.carbohydrates = carbohydrates;
		this.fiber = fiber;
		this.sugars = sugars;
		this.protein = protein;
		this.water = water;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "fat", nullable = false, precision = 12, scale = 0)
	public float getFat() {
		return this.fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	@Column(name = "cholesterol", nullable = false, precision = 12, scale = 0)
	public float getCholesterol() {
		return this.cholesterol;
	}

	public void setCholesterol(float cholesterol) {
		this.cholesterol = cholesterol;
	}

	@Column(name = "sodium", nullable = false, precision = 12, scale = 0)
	public float getSodium() {
		return this.sodium;
	}

	public void setSodium(float sodium) {
		this.sodium = sodium;
	}

	@Column(name = "potassium", nullable = false, precision = 12, scale = 0)
	public float getPotassium() {
		return this.potassium;
	}

	public void setPotassium(float potassium) {
		this.potassium = potassium;
	}

	@Column(name = "carbohydrates", nullable = false, precision = 12, scale = 0)
	public float getCarbohydrates() {
		return this.carbohydrates;
	}

	public void setCarbohydrates(float carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	@Column(name = "fiber", nullable = false, precision = 12, scale = 0)
	public float getFiber() {
		return this.fiber;
	}

	public void setFiber(float fiber) {
		this.fiber = fiber;
	}

	@Column(name = "sugars", nullable = false, precision = 12, scale = 0)
	public float getSugars() {
		return this.sugars;
	}

	public void setSugars(float sugars) {
		this.sugars = sugars;
	}

	@Column(name = "protein", nullable = false, precision = 12, scale = 0)
	public float getProtein() {
		return this.protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	@Column(name = "water", nullable = false, precision = 12, scale = 0)
	public float getWater() {
		return this.water;
	}

	public void setWater(float water) {
		this.water = water;
	}

}
