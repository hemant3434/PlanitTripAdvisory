import React from 'react';
import { View, ScrollView, Text } from "react-native";
import { SearchBar } from "react-native-elements";

class ExplorePage extends React.Component {
  constructor(props){
    super(props);
    this.state = {
        search: "",
      };
  }

  updateSearch = search => {
    this.setState({ search });
  };

  render() {
    const { search } = this.state;
    return (
      <View>
        <ScrollView>
          <SearchBar
        placeholder="Type Here..."
        onChangeText={this.updateSearch}
        value={search}
        lightTheme={true}
        onCancel={this.state.search=""}/>
        </ScrollView>
      </View>);
  }
}

export default ExplorePage
