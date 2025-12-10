import axios from "axios";
import { UserEntity } from "../models/entities/UserEntity";
import { UpdateUserRequest } from "../models/UpdateUserRequest";
import { API_USER_PATH_VAR } from "../constants/apiConstants";
import { UpdateUserSecurityRequest } from "../models/UpdateUserSecurityRequest";
import { ResetPasswordRequest } from "../models/ResetPasswordRequest";

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
}
export const userService = new UserService();