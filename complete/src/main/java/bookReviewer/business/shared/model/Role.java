package bookReviewer.business.shared.model;

public enum Role {
    USER ("user"),
    MODERATOR ("moderator"),
    ADMIN("admin");

    String rolename;

    Role(String rolename) {
        this.rolename  = rolename;
    }

    public String getRolename() {
        return this.rolename;
    }

}

