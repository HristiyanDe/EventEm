import axios from "axios";
import { UserEntity } from "../models/entities/UserEntity";
import { UpdateUserRequest } from "../models/UpdateUserRequest";

class UserService{
async updateUserProfile(userData: UpdateUserRequest, token: string | null, id: number | null): Promise<UserEntity>
{
    const response = await axios.put('/api/users/'+ id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(userData)
    });
    return response.data;
}
}
export const userService = new UserService();