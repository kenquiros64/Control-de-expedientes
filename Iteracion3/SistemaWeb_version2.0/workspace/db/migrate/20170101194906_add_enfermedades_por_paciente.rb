class AddEnfermedadesPorPaciente < ActiveRecord::Migration
  def change
    create_table :enfermedads_pacientes, id: false do |t|
      t.belongs_to :enfermedad, index: true
      t.belongs_to :paciente, index: true
    end
  end
end
