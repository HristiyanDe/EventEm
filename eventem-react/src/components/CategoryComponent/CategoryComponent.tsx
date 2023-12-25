import React, { useState } from 'react';
import {API_CATEGORIES_PATH} from '../../constants/apiConstants';
import axios from 'axios';
import { CategoryRequest } from '../../models/CategoryRequest';
import { useAuth } from '../../auth/AuthContext';
const CategoryForm: React.FC = () => {
const [formData, setFormData] = useState<CategoryRequest>({
categoryName: '',
});
const { token } = useAuth();
const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
        console.log(token);
        const response = await axios.post(API_CATEGORIES_PATH, formData, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};
const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const {name, value} = e.target;
    setFormData({
        ...formData,
        [name]:value,
    });
};
return (<form onSubmit={handleSubmit}>
    <input type="text" placeholder="Category Name" name="categoryName" value={formData.categoryName} onChange={handleInputChange}/>
    <button type="submit">Add Category</button>
    </form>)
};
export default CategoryForm;