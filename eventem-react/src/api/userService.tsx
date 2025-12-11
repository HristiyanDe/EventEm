import axios from "axios";
import { UserEntity } from "../models/entities/UserEntity";
import { UpdateUserRequest } from "../models/UpdateUserRequest";
import { API_USER_PATH_VAR, API_USERS_PATH } from "../constants/apiConstants";
import { UpdateUserSecurityRequest } from "../models/UpdateUserSecurityRequest";
import { ResetPasswordRequest } from "../models/ResetPasswordRequest";
import { AdminUserListDTO } from "../models/dtos/AdminUserListDTO";

class UserService{
async updateUserProfile(userData: UpdateUserRequest, token: string | null, id: number): Promise<UserEntity>
{
    console.log("User Id: "+id);
    const response = await axios.put(API_USER_PATH_VAR(id), userData,{
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
    });
    return response.data;
}
async updateUserSecurity(userData: UpdateUserSecurityRequest, token: string | null, id: number): Promise<string>
{
    console.log(id);
    const response = await axios.put(API_USER_PATH_VAR(id)+"/security", userData,{
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
    });
    return response.data;
}
async resetUserPassword(userData: ResetPasswordRequest): Promise<string>
{
    const response = await axios.post(`${API_USER_PATH_VAR(0)}/reset-password`, userData,{
        headers: {
            'Content-Type': 'application/json',
        },
    });
    return response.data;
}
async searchUsers(username: string, token: string | null): Promise<AdminUserListDTO[]> {
    const response = await axios.get(`${API_USERS_PATH}/search`, {
        params: { username },
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
    });
    console.log("Search Users Response: ", response.data);
    return response.data;
}
async banUser(username: string, token: string | null): Promise<AdminUserListDTO> {
    const response = await axios.post(`${API_USERS_PATH}/ban`, { username }, {
        headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
        },
    });
    return response.data;
}
}
export const userService = new UserService();