package io.github.krasnoludkolo.auth.api;

public final class RegisterRequestDTO {

    public String password;

    public RegisterRequestDTO() {
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RegisterRequestDTO)) return false;
        final RegisterRequestDTO other = (RegisterRequestDTO) o;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "RegisterRequestDTO(password=" + this.getPassword() + ")";
    }
}
