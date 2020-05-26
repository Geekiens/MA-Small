package bookReviewer.business.useCase.command.deleteBookUseCase;

import bookReviewer.business.shared.Role;

public class DeleteBookCommand {
    Long bookId;
    Role role;

    public DeleteBookCommand() {
    }

    public DeleteBookCommand(Long bookId, Role role) {
        this.bookId = bookId;
        this.role = role;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
