package com.example.sporteam.sporteam;

/**
 * Created by redbend on 7/14/16.
 */
public class GroupLayout {
        private  String CompanyName="";
        private  String type="";
        private  String Image="";

        /*********** Set Methods ******************/

        public void setCompanyName(String CompanyName)
        {
            this.CompanyName = CompanyName;
        }

        public void setImage(String Image)
        {
            this.Image = Image;
        }

        public void setType(String type)
        {
            this.type = type;
        }

        /*********** Get Methods ****************/

        public String getCompanyName()
        {
            return this.CompanyName;
        }

        public String getImage()
        {
            return this.Image;
        }

        public String getType()
        {
            return this.type;
        }
}
