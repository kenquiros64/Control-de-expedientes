class AddAttachmentImageToPacientes < ActiveRecord::Migration
  def self.up
    change_table :pacientes do |t|
      t.attachment :image
    end
  end

  def self.down
    remove_attachment :pacientes, :image
  end
end
