package mk.ukim.finki.mk.lab.repository.jpa;
import mk.ukim.finki.mk.lab.model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<EventBooking, Long> {
    @Override
    Optional<EventBooking> findById(Long aLong);
}