import {UserRequest} from "./UserRequest";

export interface RegisterRequest{
    username: string;
    password: string;
    userRequest: UserRequest;
}
