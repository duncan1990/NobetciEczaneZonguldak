package com.ahmety.nbeczzonguldak.Model;

public class Result {
    private String name;
    private String dist;
    private String address;
    private String phone;
    private String loc;
    public Result(String name, String dist, String address, String phone, String loc) {
        this.name = name;
        this.dist = dist;
        this.address = address;
        this.phone = phone;
        this.loc = loc;
    }


    public String getName() { return name; }
   // @JsonProperty("name")
    public void setName(String name) { this.name = name; }

    public String getDist() { return dist; }
    public void setDist(String dist) { this.dist = dist; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLOC() { return loc; }
    public void setLOC(String loc) { this.loc = loc; }





}
