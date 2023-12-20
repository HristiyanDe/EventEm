import React, {createContext, useContext, useState, ReactNode} from "react";
type AuthProviderProps = {
  children: ReactNode;
};
type AuthContextType = {
    token: string | null;
    setToken: (token: string | null) => void;
};
const AuthContext = createContext<AuthContextType>({
    token: null,
    setToken: () => {},
});
const AuthProvider: React.FC<AuthProviderProps> = ({children}) =>{
    const [token, setToken] = useState<string | null>(null);
    return (
        <AuthContext.Provider value = {{token, setToken}}>
    {children}
    </AuthContext.Provider>
    );
};
const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context)
    {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};
export {AuthProvider,useAuth};
