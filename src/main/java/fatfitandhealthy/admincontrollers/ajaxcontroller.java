package fatfitandhealthy.admincontrollers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fatfitandhealthy.dao.ActivityLog;
import fatfitandhealthy.dao.Admin;
import fatfitandhealthy.dao.AjaxResponseBody;
import fatfitandhealthy.dao.Breakfast;
import fatfitandhealthy.dao.Comment;
import fatfitandhealthy.dao.Dinner;
import fatfitandhealthy.dao.Exercise;
import fatfitandhealthy.dao.ExerciseLog;
import fatfitandhealthy.dao.FoodItems;
import fatfitandhealthy.dao.Like;
import fatfitandhealthy.dao.Lunch;
import fatfitandhealthy.dao.PasswordResetToken;
import fatfitandhealthy.dao.Post;
import fatfitandhealthy.dao.UserHealth;
import fatfitandhealthy.dao.UserLogin;
import fatfitandhealthy.dao.UsersPersonal;
import fatfitandhealthy.dao.Weight;
import fatfitandhealthy.hibernate.Getdata;
import fatfitandhealthy.jsonview.Views;
import fatfitandhealthy.ws.LikeAjaxResponseBody;
import fatfitandhealthy.ws.PostAjaxResponseBody;

@RestController
public class ajaxcontroller {
	@JsonView(Views.Public.class)
	@RequestMapping(value="/admin/editadmin",method=RequestMethod.POST)
	public void permission(@RequestParam int id,@RequestParam int permission)
	{
		//System.out.println(id+" "+permission);
		Admin admin=(Admin) Getdata.onecolumnvaluewhere("Admin", "id", String.valueOf(id)).iterator().next();
		admin.setPermission(permission);
		admin.setEditTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Getdata.update(admin);
		
	}
	@JsonView(Views.Public.class)
	@RequestMapping(value="/admin/admindelete",method=RequestMethod.POST)
	public void admindelete(@RequestParam int id,@RequestParam String image)
	{
		Getdata.delete("Admin", "id", id);
		File file=new File("F:\\fatfitandhealthy\\fatfitandhealthy\\src\\main\\webapp\\resources\\image\\admin"+File.separator+image);
		file.delete();
		
	}
	@JsonView(Views.Public.class)
	@RequestMapping(value="/admin/edituser",method=RequestMethod.POST)
	public void status(@RequestParam int id,@RequestParam String status)
	{
		//System.out.println(id+" "+permission);
		UserLogin ul=(UserLogin) Getdata.onecolumnvaluewhere("UserLogin", "id", String.valueOf(id)).iterator().next();
		ul.setStatus(status);
		ul.setEditTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Getdata.update(ul);
		
	}
	@JsonView(Views.Public.class)
	@RequestMapping(value="/admin/userdelete",method=RequestMethod.POST)
	public void userdelete(@RequestParam int id,@RequestParam String image)
	{
		Getdata.delete("UserLogin", "id", id);
		Getdata.delete("UserHealth", "id", id);
		Getdata.delete("UsersPersonal", "id", id);
		File file=new File("F:\\fatfitandhealthy\\fatfitandhealthy\\src\\main\\webapp\\resources\\image\\user"+File.separator+image);
		file.delete();
		
	}
	@JsonView(Views.Public.class)
	@RequestMapping(value="/admin/fooditemdelete",method=RequestMethod.POST)
	public void fooditemdelete(@RequestParam int id)
	{
		Getdata.delete("FoodItems", "id", id);		
	}
	@JsonView(Views.Public.class)
	@RequestMapping(value="/admin/exerciseitemdelete",method=RequestMethod.POST)
	public void exerciseitemdelete(@RequestParam int id)
	{
		Getdata.delete("Exercise", "id", id);		
	}
	
	//user section controller methods
	@JsonView(Views.Public.class)
	@RequestMapping(value="/home/addfood",method=RequestMethod.POST)
	public AjaxResponseBody addfood(@RequestParam int foodId,@RequestParam Float servingNo,@RequestParam int uid,@RequestParam String slot)
	{
		//UserHealth uh=(UserHealth)Getdata.onecolumnvaluewhere("UserHealth", "id", Integer.toString(uid)).iterator().next();		
		UserHealth uh=new UserHealth();
		uh.setId(uid); 							//uparni 2 column temporary nakheli 6, eni uparni column temporary kadheli 6
		FoodItems foodItems=(FoodItems)Getdata.onecolumnvaluewhere("FoodItems", "id", Integer.toString(foodId)).iterator().next();
		Float calplus=foodItems.getCal()*servingNo;
		System.out.println(foodId+" "+servingNo+" "+uid+" "+slot);
		if (slot.equals("breakfast")) {
			Breakfast breakfast=new Breakfast();
			
			breakfast.setUserHealth(uh);
			breakfast.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			breakfast.setFoodItems(foodItems);
			breakfast.setServingNo(servingNo);
			Getdata.save(breakfast);
		
		
		if (Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).isEmpty()) {
			ActivityLog al=new ActivityLog(uh, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), Float.toString(calplus), "0", "0", "0", 0, 0);
			Getdata.save(al);
			
		}
		else{
			ActivityLog al=(ActivityLog)Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).iterator().next();
			al.setBreakfast(Float.toString((Float.parseFloat(al.getBreakfast()))+calplus));
			Getdata.update(al);
		}
		
		}
		//adding food in lunch
		else if (slot.equals("lunch")) {
			Lunch lunch=new Lunch();
			
			lunch.setUserHealth(uh);
			lunch.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			lunch.setFoodItems(foodItems);
			lunch.setServingNo(servingNo);
			Getdata.save(lunch);
		
		
		if (Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).isEmpty()) {
			ActivityLog al=new ActivityLog(uh, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "0", Float.toString(calplus), "0", "0", 0, 0);
			Getdata.save(al);
			
		}
		else{
			ActivityLog al=(ActivityLog)Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).iterator().next();
			al.setLunch(Float.toString((Float.parseFloat(al.getLunch()))+calplus));
			Getdata.update(al);
		}
		
		}
		else if (slot.equals("dinner")) {
			Dinner dinner=new Dinner();
			
			dinner.setUserHealth(uh);
			dinner.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			dinner.setFoodItems(foodItems);
			dinner.setServingNo(servingNo);
			Getdata.save(dinner);
		
		
		if (Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).isEmpty()) {
			ActivityLog al=new ActivityLog(uh, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "0","0" , Float.toString(calplus), "0", 0, 0);
			Getdata.save(al);
			
		}
		else{
			ActivityLog al=(ActivityLog)Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).iterator().next();
			al.setDinner(Float.toString((Float.parseFloat(al.getDinner()))+calplus));
			Getdata.update(al);
		}
		
		}
		AjaxResponseBody result=new AjaxResponseBody("", "200", calplus);
		return result;
	}
	
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/home/addexercise",method=RequestMethod.POST)
	public AjaxResponseBody addexercise(@RequestParam int exerciseId,@RequestParam Float minutes,@RequestParam int uid)
	{
		UserHealth uh=(UserHealth)Getdata.onecolumnvaluewhere("UserHealth", "id", Integer.toString(uid)).iterator().next();		
		/*UserHealth uh=new UserHealth();
		uh.setId(uid);*/ 							//uparni 2 column temporary nakheli 6, eni uparni column temporary kadheli 6
		Exercise exercise=(Exercise)Getdata.onecolumnvaluewhere("Exercise", "id", Integer.toString(exerciseId)).iterator().next();
		float calplus= (float) (0.0175*exercise.getMet()*Float.parseFloat(uh.getWeight())*minutes);
		//System.out.println(foodId+" "+servingNo+" "+uid+" "+slot);
		
			ExerciseLog el=new ExerciseLog();
			
			el.setUserHealth(uh);
			el.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			el.setExercise(exercise);;
			el.setMinutes(minutes);
			Getdata.save(el);
		
		
		if (Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).isEmpty()) {
			ActivityLog al=new ActivityLog(uh, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "0", "0", "0",Float.toString(calplus) , 0, 0);
			Getdata.save(al);
			
		}
		else{
			ActivityLog al=(ActivityLog)Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).iterator().next();
			al.setExercise(Float.toString((Float.parseFloat(al.getExercise()))+calplus));
			Getdata.update(al);
		}
		
		
		
		
		AjaxResponseBody result=new AjaxResponseBody("", "200", calplus);
		return result;
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/home/addwater",method=RequestMethod.POST)
	public void addwater(@RequestParam int water,@RequestParam int uid)
	{
		
		
		//System.out.println(foodId+" "+servingNo+" "+uid+" "+slot);
		UserHealth uh=(UserHealth) Getdata.onecolumnvaluewhere("UserHealth", "id", Integer.toString(uid)).iterator().next();
		
	/*	UserHealth uh=new UserHealth();
		uh.setId(uid);*/
		
		if (Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).isEmpty()) {
			ActivityLog al=new ActivityLog(uh, new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "0", "0", "0","0" , water, 0);
			Getdata.save(al);
			
		}
		else{
			ActivityLog al=(ActivityLog)Getdata.twocolumnvaluewhere("ActivityLog", "date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()),"uid",Integer.toString(uid)).iterator().next();
			al.setWater(al.getWater()+water);
			Getdata.update(al);
		}
		
		
		
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/editgoal",method=RequestMethod.POST)
	public AjaxResponseBody editgoal(@RequestParam int id,@RequestParam String activityFactor,@RequestParam String weightGoal,@RequestParam String kgs)
	{
		
		//System.out.println(id+" "+activityFactor+" "+weightGoal+" "+kgs);
		UserHealth uh=(UserHealth) Getdata.onecolumnvaluewhere("UserHealth", "id", Integer.toString(id)).iterator().next();
		uh.setActivityFactor(activityFactor);
		uh.setWeightGoal(weightGoal);
		uh.setKgs(kgs);
		//calculating daily cal goal
		String dob=((UsersPersonal)Getdata.onecolumnvaluewhere("UsersPersonal", "id", Integer.toString(id)).iterator().next()).getDob();
		String gender=((UsersPersonal)Getdata.onecolumnvaluewhere("UsersPersonal", "id", Integer.toString(id)).iterator().next()).getGender();
		String weight=uh.getWeight();
		String height=uh.getHeight();
		double dailyCalGoal=Getdata.calcuatecalgoal(dob, gender, weight, height, activityFactor, weightGoal, kgs);
		uh.setDailyCalGoal(Double.toString(Math.round(dailyCalGoal)));
		Getdata.update(uh);
		AjaxResponseBody result=new AjaxResponseBody("", "200", (float) Math.round(dailyCalGoal));
		return result;
		
		
		
		
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/editweight",method=RequestMethod.POST)
	public AjaxResponseBody editweight(@RequestParam int id,@RequestParam String weight)
	{
		
		//System.out.println(id+" "+activityFactor+" "+weightGoal+" "+kgs);
		UserHealth uh=(UserHealth) Getdata.onecolumnvaluewhere("UserHealth", "id", Integer.toString(id)).iterator().next();
		
		
		uh.setWeight(weight);
		//calculating daily cal goal
		String dob=((UsersPersonal)Getdata.onecolumnvaluewhere("UsersPersonal", "id", Integer.toString(id)).iterator().next()).getDob();
		String gender=((UsersPersonal)Getdata.onecolumnvaluewhere("UsersPersonal", "id", Integer.toString(id)).iterator().next()).getGender();
		String activityFactor=uh.getActivityFactor();
		String weightGoal=uh.getWeightGoal();
		String kgs=uh.getKgs();
		String height=uh.getHeight();
		double dailyCalGoal=Getdata.calcuatecalgoal(dob, gender, weight, height, activityFactor, weightGoal, kgs);
		uh.setDailyCalGoal(Double.toString(Math.round(dailyCalGoal)));
		Getdata.update(uh);
		Weight w=new Weight();
		w.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		w.setUserHealth(uh);
		w.setWeight(weight);
		Getdata.save(w);
		AjaxResponseBody result=new AjaxResponseBody("", "200", (float) Math.round(dailyCalGoal));
		return result;
		
		
		
		
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/fetchposts",method=RequestMethod.POST)
	public PostAjaxResponseBody fetchposts()
	{
		
		//System.out.println(id+" "+activityFactor+" "+weightGoal+" "+kgs);
		List<Object> l=Getdata.getpostencoder();
		/*Iterator i=l.iterator();
		for (Object object : l) {
			Object[] o = (Object[]) object;
			System.out.println(o[0]+" "+o[1]+" "+o[2]+" "+o[3]+" "+o[4]+" "+o[5]+" "+o[6]+" "+o[7]+" "+o[8]+"\n");
		}*/
		PostAjaxResponseBody result=new PostAjaxResponseBody("", "200", l);
		return result;
		
		
		
		
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/fetchcomments",method=RequestMethod.POST)
	public PostAjaxResponseBody fetchcomments(@RequestParam int postid)
	{
		
		//System.out.println(id+" "+activityFactor+" "+weightGoal+" "+kgs);
		List<Object> l=Getdata.fetchcomments(postid);
		/*Iterator i=l.iterator();
		for (Object object : l) {
			Object[] o = (Object[]) object;
			System.out.println(o[0]+" "+o[1]+" "+o[2]+" "+o[3]+" "+o[4]+" "+o[5]+" "+o[6]+" "+o[7]+" "+o[8]+"\n");
		}*/
		PostAjaxResponseBody result=new PostAjaxResponseBody("", "200", l);
		return result;
		
		
		
		
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/fetchlike",method=RequestMethod.POST)
	public PostAjaxResponseBody fetchlike(@RequestParam int uid)
	{
		
		//System.out.println(id+" "+activityFactor+" "+weightGoal+" "+kgs);
		List<Object> l1=Getdata.fetchlikes(uid);
		/*Iterator i=l.iterator();
		for (Like object : l) {
			System.out.println(object.getId());
		}*/
		//List<Object> l=new ArrayList<Object>(l1);
		PostAjaxResponseBody result=new PostAjaxResponseBody("", "200", l1);
		return result;
		
		
		
		
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/editcomment",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> editcomment(@RequestParam int cid,@RequestParam String ctext)
	{
		
		//System.out.println(cid+" "+ctext);
		Comment c=(Comment) Getdata.onecolumnvaluewhere("Comment", "id", Integer.toString(cid)).iterator().next();
		c.setCtext(ctext);
		c.setUtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Getdata.update(c);
		Map<String, String> e=new HashMap<String,String>();
		e.put("ctime", c.getCtime());
		e.put("id", Integer.toString(c.getId()));
		e.put("uid", Integer.toString(c.getUid()));
		e.put("postid", Integer.toString(c.getPost().getId()));
		e.put("ctext", c.getCtext());
		e.put("utime", c.getUtime());
		return e;
		
		
		
		
		
	
	}
	@JsonView(Views.Public.class)
	@RequestMapping(value="/deletecomment",method=RequestMethod.POST)
	public void deletecomment(@RequestParam int cid)
	{
		Getdata.delete("Comment", "id", cid);	
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/deletepost",method=RequestMethod.POST)
	public void deletepost(@RequestParam int postid)
	{
		Getdata.delete("Comment", "postid", postid);
		Getdata.delete("Like", "postid", postid);
		Getdata.delete("Post", "id", postid);
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/updatepost",method=RequestMethod.POST)
	public void updatepost(@RequestParam int postid,@RequestParam String ptext)
	{
		Post post=(Post) Getdata.onecolumnvaluewhere("Post", "id", Integer.toString(postid)).iterator().next();
		post.setPtext(ptext);
		post.setUtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Getdata.update(post);
		
	
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/resetpassword",method=RequestMethod.POST)
	public void resetpassword(@RequestParam String password,@RequestParam String token)
	{
		
		PasswordResetToken passToken=(PasswordResetToken) Getdata.onecolumnvaluewhere("PasswordResetToken", "token", token).iterator().next();
		UserLogin ul=(UserLogin) Getdata.onecolumnvaluewhere("UserLogin", "id", Integer.toString(passToken.getUserHealth().getId())).iterator().next();
		//System.out.println(ul.getPassword());
		ul.setPassword(password);
		ul.setEditTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		Getdata.update(ul);
		Getdata.delete("PasswordResetToken", "id", passToken.getId());
	
	}
}
	