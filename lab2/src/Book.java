import java.util.Objects;

public final class Book {
    private String title;
    private Author author;

    public Book(String title, Author author) {
        setTitle(title);
        setAuthor(author);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название книги не должно быть пустым");
        }
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = Objects.requireNonNull(author, "Автор не указан");
    }

    @Override
    public String toString() {
        return title + ": " + author;
    }
}
