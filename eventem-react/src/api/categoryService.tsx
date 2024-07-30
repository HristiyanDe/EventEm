import { API_CATEGORIES_PATH } from "../constants/apiConstants";
import axios from "axios";
import { CategoryRequest } from "../models/CategoryRequest";

class CategoryService {
  

  async getCategories(): Promise<CategoryRequest[]> {

    const response = await axios.get<CategoryRequest[]>(API_CATEGORIES_PATH);
    return response.data;
}
}
export const categoryService = new CategoryService();
