package example9.grails.user

class User {

    String username

    String email

    String password

    Set roles = []

    static hasMany = [roles: Role]

    static constraints = {
        username nullable: false, blank: false, unique: true, maxSize: 20
        email nullable: false, blank: false, unique: true, maxSize: 20, email: true

        // At least one upper case English letter, (?=.*?[A-Z])
        // At least one lower case English letter, (?=.*?[a-z])
        // At least one digit, (?=.*?[0-9])
        // At least one special character, (?=.*?[#?!@$%^&*-])
        // Minimum eight in length .{8,} (with the anchors)
        password(nullable: false, blank: false, validator: { val, obj, errors ->
            if (val.size() < 6) {
                errors.rejectValue("password", "validation.password.short")
            }
            // ...
        })
        // more https://docs.grails.org/4.0.10/guide/validation.html
        roles minSize: 1
    }

    static mapping = {
        roles joinTable: [name: "user_roles", key: "user_id"]
    }
}
