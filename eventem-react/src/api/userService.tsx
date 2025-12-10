import axios from "axios";
import { UserEntity } from "../models/entities/UserEntity";
import { UpdateUserRequest } from "../models/UpdateUserRequest";
import { API_USER_PATH_VAR } from "../constants/apiConstants";
import { UpdateUserSecurityRequest } from "../models/UpdateUserSecurityRequest";

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
}
export const userService = new UserService();