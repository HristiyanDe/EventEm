import React, {createContext, useContext, useState, ReactNode} from "react";
import Cookies from "js-cookie";
import { UserEntity } from '../models/entities/UserEntity';
type AuthProviderProps = {
  children: ReactNode;
};
type AuthContextType = {
    token: string | null;
    setToken: (token: string | null) => void;
    user: UserEntity | null;
    setUser: (user: UserEntity | null) => void;
};
const AuthContext = createContext<AuthContextType>({
    token: null,
    setToken: () => {},
    user: null,
    setUser: ()=> {},
});
const AuthProvider: React.FC<AuthProviderProps> = ({children}) =>{
    const {token, setToken, user, setUser} = useAuth();
    return (
        <AuthContext.Provider value = {{token, setToken, user, setUser}}>
    {children}
    </AuthContext.Provider>
    );
};
const useAuth = () => {
    const [token, setToken] = useState<string | null>(Cookies.get('token') || null);
    const [user, setUser] = useState<UserEntity | null>(() => {
        const userCookie = Cookies.get('user');
        try {
        return userCookie ? JSON.parse(userCookie) as UserEntity : null;
        } catch (error) {
        console.error('Error parsing user cookie:', error);
        return null;
        }
    })
    
    return { token, setToken, user, setUser };
};
export {AuthProvider,useAuth};
