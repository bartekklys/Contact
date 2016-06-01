public class Contact {
    private String name;
    private String phone;
    private String email;
    private Category category;

    public Contact(String name, String phone, String email, Category category) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", email=" + email +
                ", category=" + category +
                '}';
    }
}
