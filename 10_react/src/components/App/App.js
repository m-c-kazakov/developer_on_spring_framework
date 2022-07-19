import './App.css';
import React, {useState} from "react";
import CustomInput from "../CustomInput/CustomInput";
import CustomButton from "../CustomButton/CustomButton";
import CustomTable from "../CustomTable/CustomTable";


const initialValues = {
    id: '',
    name: '',
    author: '',
    genre: ''

}


function App() {

    const [bookData, setBookData] = useState(initialValues);
    const [books, setBooks] = useState([])
    const [editableBookData, setEditableBookData] = useState({
        isEdit: false,
        bookId: null
    })


    const isFilledFields = bookData.name && bookData.author && bookData.genre

    const handleSubmitBook = (event) => {
        event.preventDefault();
        if (isFilledFields) {
            if (editableBookData.isEdit) {
                const editedBooks = books;
                editedBooks.splice(editableBookData.bookId, 1, bookData);
                setBooks(editedBooks);
                setEditableBookData({
                    isEdit: false,
                    bookId: null
                });
            } else {
                setBooks(prevState => [...prevState, bookData]);
            }
            setBookData(initialValues)
        }
    };

    const handleCleanClick = () => setBookData(initialValues);

    const handleRemoveClick = ({id}) => {
        setBooks(books.filter((book, bookId) => bookId !== id));
    }

    const handleEditClick = ({book, id}) => {
        setBookData(book)
        setEditableBookData({
            isEdit: true,
            bookId: id
        });
    };

    const handleInputChange = (event, fieldName) => {
        setBookData(prevState => ({
            ...prevState,
            [fieldName]: event.target.value
        }))
    }


    return (
        <div className={"wrapper"}>
            <div className={"wrapper-content"}>
                <div className={"table-data"}>
                    <CustomTable
                        books={books}
                        handleRemoveClick={handleRemoveClick}
                        handleEditClick={handleEditClick}
                    />
                </div>

                <div>
                    <form onSubmit={handleSubmitBook} onReset={handleCleanClick}>
                        <CustomInput
                            placeholder={"Write Book Name"}
                            handleChange={handleInputChange}
                            value={bookData.name}
                            fieldName={"name"}
                        />
                        <CustomInput
                            placeholder={"Write Book Author"}
                            handleChange={handleInputChange}
                            value={bookData.author}
                            fieldName={"author"}
                        />
                        <CustomInput
                            placeholder={"Write Book Genre"}
                            handleChange={handleInputChange}
                            value={bookData.genre}
                            fieldName={"genre"}
                        />
                        <div className={"buttons-wrapper"}>
                            <CustomButton
                                label={"Clean"}
                                type={"reset"}
                            />
                            <CustomButton
                                label={editableBookData.isEdit ? 'Update' : 'Save'}
                                disabled={!isFilledFields}
                                type={"submit"}
                            />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default App;
