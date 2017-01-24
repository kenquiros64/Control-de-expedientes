class AddPacientesToMedicos < ActiveRecord::Migration
  def change
     create_table :medicos_pacientes, id: false do |t|
      t.belongs_to :medico, index: true
      t.belongs_to :paciente, index: true
    end
  end
end
