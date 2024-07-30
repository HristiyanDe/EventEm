import React, {createContext, useContext, useState, ReactNode} from "react";
import Cookies from "js-cookie";
type AuthProviderProps = {
  children: ReactNode;
};
type AuthContextType = {
    token: string | null;
    setToken: (token: string | null) => void;
    userId: number | null;
    setUser: (userId: number | null) => void;
};
const AuthContext = createContext<AuthContextType>({
    token: null,
    setToken: () => {},
    userId: null,
    setUser: ()=> {},
});
const AuthProvider: React.FC<AuthProviderProps> = ({children}) =>{
    const {token, setToken, userId, setUser} = useAuth();
    return (
        <AuthContext.Provider value = {{token, setToken,userId, setUser}}>
    {children}
    </AuthContext.Provider>
    );
};
const useAuth = () => {
    const [token, setToken] = useState<string | null>(Cookies.get('token') || null);
    const [userId, setUser] = useState<number | null>(Cookies.get('userId') ? parseInt(Cookies.get('userId') || '') : null);
    
    return { token, setToken, userId, setUser };
};
export {AuthProvider,useAuth};
