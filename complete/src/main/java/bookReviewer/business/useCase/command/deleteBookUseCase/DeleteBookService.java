package bookReviewer.business.useCase.command.deleteBookUseCase;

import bookReviewer.business.boundary.in.useCase.command.DeleteBookUseCase;
import bookReviewer.business.boundary.out.persistence.DeleteBookById;
import bookReviewer.business.boundary.out.persistence.FindBookById;
import bookReviewer.business.exception.ResourceNotFoundException;
import bookReviewer.business.mapper.businessToEntity.RoleMapper;
import bookReviewer.business.shared.authorizer.CheckRole;
import bookReviewer.entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteBookService")
public class DeleteBookService implements DeleteBookUseCase {

    @Autowired
    @Qualifier("FindBookByIdService")
    FindBookById findBookById;

    @Autowired
    @Qualifier("DeleteBookByIdService")
    DeleteBookById deleteBookById;

    public void deleteBook(DeleteBookCommand deleteBookCommand) {
        CheckRole.checkHasMinimumRequiredRole(RoleMapper.map(deleteBookCommand.getRole()), Role.MODERATOR);
        findBookById.findBookById(deleteBookCommand.getBookId()).orElseThrow(() -> new ResourceNotFoundException("book doesn't exist with id: " + deleteBookCommand.getBookId()));
        deleteBookById.deleteBookById(deleteBookCommand.getBookId());
    }

}
