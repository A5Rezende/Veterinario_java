package View;

import java.util.List;
import model.Veterinario;
import model.VeterinarioDAO;

/**
 *
 * @author Antonio
 */
public class VeterinarioTableModel extends GenericTableModel {

    public VeterinarioTableModel(List vDados) {
        super(vDados, new String[]{"Id", "Nome", "Endereco", "Email", "Telefone"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Veterinario veterinario = (Veterinario) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return veterinario.getId();
            case 1:
                return veterinario.getNome();
            case 2:
                return veterinario.getEndereco();
            case 3:
                return veterinario.getEmail();
            case 4:
                return veterinario.getTelefone();
            default:
                return "";
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Veterinario veterinario = (Veterinario) vDados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                veterinario.setId((int) aValue);
                break;
            case 1:
                veterinario.setNome((String) aValue);
                break;
            case 2:
                veterinario.setEndereco((String) aValue);
                break;
            case 3:
                veterinario.setEmail((String) aValue);
                break;
            case 4:
                veterinario.setTelefone((String) aValue);
                break;
            default:
                break;
        }

        VeterinarioDAO.getInstance().update(veterinario);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}