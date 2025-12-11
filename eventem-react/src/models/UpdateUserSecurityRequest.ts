export interface UpdateUserSecurityRequest{
    username: string,
    password: string,
    newPassword: string | null,
}