package com.diabin.latte.ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by yingping on 2017/11/26.
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {
   @Id
    private  long useId = 0;
    private String name =null;
    private String avatar = null;
    private String gender = null;
    private String address = null;
   @Generated(hash = 614754483)
   public UserProfile(long useId, String name, String avatar, String gender,
           String address) {
       this.useId = useId;
       this.name = name;
       this.avatar = avatar;
       this.gender = gender;
       this.address = address;
   }
   @Generated(hash = 968487393)
   public UserProfile() {
   }
   public long getUseId() {
       return this.useId;
   }
   public void setUseId(long useId) {
       this.useId = useId;
   }
   public String getName() {
       return this.name;
   }
   public void setName(String name) {
       this.name = name;
   }
   public String getAvatar() {
       return this.avatar;
   }
   public void setAvatar(String avatar) {
       this.avatar = avatar;
   }
   public String getGender() {
       return this.gender;
   }
   public void setGender(String gender) {
       this.gender = gender;
   }
   public String getAddress() {
       return this.address;
   }
   public void setAddress(String address) {
       this.address = address;
   }

}
