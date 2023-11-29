package org.cursoutn.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.cursoutn.repository.JpaClienteRepository;

import java.util.List;

import static org.cursoutn.Main.getEntityManager;

@Getter
@Setter
@Entity
@Table(name="cliente")
public class ClienteModel {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String razon_social;

    @Column(nullable = false)
    private Long cuil;

    @OneToMany
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private List<NotificacionModel> notificaciones;

    private JpaClienteRepository repository;

    @ManyToMany
    @JoinTable(
            name = "cliente_servicio",
            joinColumns = @JoinColumn(name = "servicio_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    )
    private List<ServicioModel> servicios;

    @OneToMany
    @JoinColumn(name="cliente_id", referencedColumnName = "id")
    private List<IncidenteModel> incidentes;

    public ClienteModel() {
    }

    public ClienteModel(String razon_social, Long cuil, List<NotificacionModel> notificaciones,
                        List<ServicioModel> servicios, List<IncidenteModel> incidentes) {
        this.razon_social = razon_social;
        this.cuil = cuil;
        this.notificaciones = notificaciones;
        this.servicios = servicios;
        this.incidentes = incidentes;
    }

    //Para crear un cliente por primera vez
    public ClienteModel(String razon_social, Long cuil) {
        this.razon_social = razon_social;
        this.cuil = cuil;
    }

    EntityManager entityManager = getEntityManager();
    JpaClienteRepository repo = new JpaClienteRepository();

    public void guardarCliente (ClienteModel cliente) throws Exception {
            repository.guardarCliente(cliente);
    }
    public ClienteModel obtenerClientePorId(Integer id) throws Exception {
        return repository.obtenerClientePorId(id);
    }
    public void actualizarCliente(ClienteModel cliente) throws Exception {
        repository.actualizarCliente(cliente);
    }
    public void eliminarCliente(ClienteModel cliente) throws Exception {repository.eliminarCliente(cliente);}
}
